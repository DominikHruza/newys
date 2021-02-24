package com.assignment.Newys.controllers;

import com.assignment.Newys.DTO.ArticleDto;
import com.assignment.Newys.services.NewsArticleService;
import com.assignment.Newys.services.facades.ArticleCrudFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/article")
public class NewsArticleController {

    private final NewsArticleService newsArticleService;
    private final ArticleCrudFacade articleCrudFacade;

    @Autowired
    public NewsArticleController(NewsArticleService newsArticleService,
                                 ArticleCrudFacade articleCrudFacade) {
        this.newsArticleService = newsArticleService;
        this.articleCrudFacade = articleCrudFacade;
    }

    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleDto addUser(@RequestBody ArticleDto articleDto){
       return articleCrudFacade.AddNewsArticle(articleDto);
    }

    @PutMapping(value = "/{articleId}")
    @ResponseStatus(HttpStatus.OK)
    public ArticleDto updateMemberSkills(
            @PathVariable Long articleId,
            @RequestBody ArticleDto articleDto,
            Principal principal){

        return articleCrudFacade.updateNewsArticle(
                articleId,
                articleDto,
                principal.getName()
        );
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id){
        newsArticleService.deleteArticle(id);
    }
}
