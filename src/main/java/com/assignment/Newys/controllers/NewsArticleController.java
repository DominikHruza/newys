package com.assignment.Newys.controllers;

import com.assignment.Newys.DTO.ArticleDto;
import com.assignment.Newys.services.NewsArticleService;
import com.assignment.Newys.services.facades.AddNewArticleFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/article")
public class NewsArticleController {

    private final NewsArticleService newsArticleService;
    private final AddNewArticleFacade addNewArticleFacade;



    @Autowired
    public NewsArticleController(NewsArticleService newsArticleService,
                                 AddNewArticleFacade addNewArticleFacade) {
        this.newsArticleService = newsArticleService;
        this.addNewArticleFacade = addNewArticleFacade;
    }

    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody ArticleDto articleDto){
       addNewArticleFacade.AddNewsArticle(articleDto);
    }

}
