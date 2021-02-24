package com.assignment.Newys.services.facades;

import com.assignment.Newys.DTO.ArticleDto;
import com.assignment.Newys.exceptions.NotFoundInDbException;
import com.assignment.Newys.models.NewsArticle;
import com.assignment.Newys.models.User;
import com.assignment.Newys.repository.UserRepository;
import com.assignment.Newys.services.NewsArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleCrudFacadeImpl implements ArticleCrudFacade {

    private final NewsArticleService newsArticleService;
    private final UserRepository userRepository;

    @Autowired
    public ArticleCrudFacadeImpl(NewsArticleService newsArticleService,
                                 UserRepository userRepository) {
        this.newsArticleService = newsArticleService;
        this.userRepository = userRepository;
    }

    @Override
    public ArticleDto AddNewsArticle(ArticleDto articleDto) {
        String username = articleDto.getUsername();
        User user = CheckIfUserExists(username);

        NewsArticle savedArticle = newsArticleService.addNewArticle(articleDto, user);
        return ArticleDto.ConvertToDto(savedArticle);
    }

    @Override
    public ArticleDto updateNewsArticle(Long articleId,
                                        ArticleDto articleDto,
                                        String username) {

        User user = CheckIfUserExists(username);
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


    private User CheckIfUserExists(String username){
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(!optionalUser.isPresent()){
            throw new NotFoundInDbException("User with username " + username + " does not exists");
        }
       return optionalUser.get();
    }
}
