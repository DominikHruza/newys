package com.assignment.Newys.services.facades;

import com.assignment.Newys.models.NewsArticle;
import com.assignment.Newys.models.User;
import com.assignment.Newys.services.AppUserService;
import com.assignment.Newys.services.NewsArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleLikesFacadeImpl implements ArticleLikesFacade {

    private final NewsArticleService newsArticleService;
    private final AppUserService appUserService;

    @Autowired
    public ArticleLikesFacadeImpl(NewsArticleService newsArticleService,
                                 AppUserService appUserService
    ) {
        this.newsArticleService = newsArticleService;
        this.appUserService = appUserService;
    }

    @Override
    public void addNewLike(Long articleId, String username) {
        User user = appUserService.findUserIfExists(username);
        NewsArticle article = newsArticleService.saveLike(articleId, user);
        appUserService.saveLikedArticle(user,article);
    }

    @Override
    public void removeLike(Long articleId,  String username) {
        User user = appUserService.findUserIfExists(username);
        NewsArticle article = newsArticleService.deleteLike(articleId, user);
        appUserService.deleteLikedArticle(user,article);
    }
}
