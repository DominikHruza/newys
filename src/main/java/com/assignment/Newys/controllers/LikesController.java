package com.assignment.Newys.controllers;


import com.assignment.Newys.DTO.UserDto;
import com.assignment.Newys.services.NewsArticleService;
import com.assignment.Newys.services.facades.ArticleLikesFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/likes")
public class LikesController {

    private final NewsArticleService newsArticleService;
    private final ArticleLikesFacade articleLikesFacade;

    @Autowired
    public LikesController(NewsArticleService newsArticleService,
                           ArticleLikesFacade articleLikesFacade) {
        this.newsArticleService = newsArticleService;
        this.articleLikesFacade = articleLikesFacade;
    }

    @GetMapping(path = "/{articleId}")
    public List<UserDto> getAllLikes(@PathVariable Long articleId){
        return newsArticleService.getAllLikes(articleId);
    }

    @PostMapping(value = "/{articleId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addLike(Principal principal,
                        @PathVariable Long articleId){
        articleLikesFacade.addNewLike(articleId, principal.getName());
    }

    @DeleteMapping(path = "/{articleId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLike(Principal principal,
                           @PathVariable Long articleId){
        articleLikesFacade.removeLike(articleId, principal.getName());
    }
}
