package com.assignment.Newys.services;

import com.assignment.Newys.DTO.ArticleDto;
import com.assignment.Newys.models.NewsArticle;
import com.assignment.Newys.models.User;

import java.util.List;

public interface NewsArticleService {
    List<NewsArticle> getAllArticles();
    public NewsArticle addNewArticle(ArticleDto articleDto, User user);
    public NewsArticle updateArticle(ArticleDto articleDto, Long articleId);
    public void deleteArticle(Long id);
}
