package com.assignment.Newys.services.facades;

import com.assignment.Newys.DTO.ArticleDto;

public interface ArticleCrudFacade {

    ArticleDto AddNewsArticle(ArticleDto articleDto);
    ArticleDto updateNewsArticle(Long articleId, ArticleDto articleDto, String username);
}
