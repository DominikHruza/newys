package com.assignment.Newys.services;

import com.assignment.Newys.DTO.ArticleDto;
import com.assignment.Newys.models.NewsArticle;
import com.assignment.Newys.models.User;

import java.util.List;

public interface NewsArticleService {
    List<NewsArticle> getAllArticles();
    public void addNewArticle(ArticleDto articleDto, User user);
}
