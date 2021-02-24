package com.assignment.Newys.controllers;

import com.assignment.Newys.DTO.MessageDto;
import com.assignment.Newys.DTO.UserDto;
import com.assignment.Newys.security.services.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/user")
public class UserController {

    private final AppUserService userService;

    public UserController(AppUserService userService) {
        this.userService = userService;
    }


//    @GetMapping(path = "/all")
//    public List<NewsArticle> getAll(){
//
//    }

    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody UserDto user){
        userService.registerNewUser(user);
    }

    @DeleteMapping(path = "/{username}")
    public MessageDto deleteUser(@PathVariable String username){
       return userService.deleteUser(username);
    }
}
