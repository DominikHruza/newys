package com.assignment.Newys.services;

import com.assignment.Newys.DTO.ArticleDto;
import com.assignment.Newys.DTO.UserDto;
import com.assignment.Newys.models.NewsArticle;
import com.assignment.Newys.models.User;
import com.assignment.Newys.models.UserGroup;

import java.util.List;

public interface NewsArticleService {
    List<ArticleDto> getAllArticles();
    ArticleDto getArticleById(Long id);
    public NewsArticle addNewArticle(ArticleDto articleDto, User user);
    public NewsArticle updateArticle(ArticleDto articleDto, Long articleId);
    public void deleteArticle(Long id);
    public NewsArticle saveLike(Long articleId, User user);
    public NewsArticle deleteLike(Long articleId, User user);
    public List<UserDto> getAllLikes(Long articleId);
    public void addToGroup(Long articleId, UserGroup userGroup);
}
