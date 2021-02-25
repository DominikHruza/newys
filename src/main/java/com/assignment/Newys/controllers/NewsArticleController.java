package com.assignment.Newys.controllers;

import com.assignment.Newys.DTO.ArticleDto;
import com.assignment.Newys.services.NewsArticleService;
import com.assignment.Newys.services.facades.ArticleCrudFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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

    @GetMapping(path = "/all")
    public List<ArticleDto> getAll(){
        return newsArticleService.getAllArticles();
    }

    @GetMapping(path = "/{articleId}")
    public ArticleDto getSingleArticle(
            @PathVariable Long articleId){
        return newsArticleService.getArticleById(articleId);
    }

    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleDto addUser(@RequestBody ArticleDto articleDto){
       return articleCrudFacade.AddNewsArticle(articleDto);
    }

    @PutMapping(value = "/{articleId}")
    @ResponseStatus(HttpStatus.OK)
    public ArticleDto updateArticle(
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
    public void deleteArticle(@PathVariable Long id){
        newsArticleService.deleteArticle(id);
    }
}
