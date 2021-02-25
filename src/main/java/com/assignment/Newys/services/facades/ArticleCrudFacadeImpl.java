package com.assignment.Newys.services.facades;

import com.assignment.Newys.DTO.ArticleDto;
import com.assignment.Newys.exceptions.NotFoundInDbException;
import com.assignment.Newys.models.NewsArticle;
import com.assignment.Newys.models.User;
import com.assignment.Newys.services.AppUserService;
import com.assignment.Newys.services.NewsArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleCrudFacadeImpl implements ArticleCrudFacade {

    private final NewsArticleService newsArticleService;
    private final AppUserService appUserService;

    @Autowired
    public ArticleCrudFacadeImpl(NewsArticleService newsArticleService,
                                 AppUserService appUserService
                                ) {
        this.newsArticleService = newsArticleService;
        this.appUserService = appUserService;
    }

    @Override
    public ArticleDto AddNewsArticle(ArticleDto articleDto) {
        String username = articleDto.getUsername();
        User user = appUserService.findUserIfExists(username);

        NewsArticle savedArticle = newsArticleService.addNewArticle(articleDto, user);
        return ArticleDto.ConvertToDto(savedArticle);
    }

    @Override
    public ArticleDto updateNewsArticle(Long articleId,
                                        ArticleDto articleDto,
                                        String username) {

        User user = appUserService.findUserIfExists(username);
        CheckIfArticleBelongsToUser(user, articleId);

        NewsArticle article = newsArticleService.updateArticle(articleDto, articleId);
        return ArticleDto.ConvertToDto(article);
    }

    private void CheckIfArticleBelongsToUser(User user,
                                             Long articleId) {
        Optional<NewsArticle> foundArticle =
                user.getArticles()
                    .stream()
                    .filter(article -> article.getId().equals(articleId))
                    .findFirst();

        if(!foundArticle.isPresent()){
            throw new NotFoundInDbException(
                    "Article with id " + articleId + " does not belong to current user"
            );
        }
    }
}
