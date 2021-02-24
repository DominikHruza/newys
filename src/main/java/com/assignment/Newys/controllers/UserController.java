package com.assignment.Newys.controllers;

import com.assignment.Newys.models.NewsArticle;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/admin/user")
public class UserController {

    List<NewsArticle> articles = Arrays.asList(
            new NewsArticle(1L, "fj;as;lfjalk;sdf"),
            new NewsArticle(2L, "afj;asdljfdhjasdkf")
    );

    @GetMapping(path = "/all")
    public List<NewsArticle> getAll(){
        return articles;
    }

    @PostMapping(path = "/")
    public void addUser(){

    }

    @DeleteMapping(path = "/")
    public void deleteUser(){

    }
}
