package com.assignment.Newys.services;

import com.assignment.Newys.DTO.MessageDto;
import com.assignment.Newys.DTO.UserDto;
import com.assignment.Newys.models.NewsArticle;
import com.assignment.Newys.models.User;

public interface UserService {
    void registerNewUser(UserDto userDto);
    MessageDto deleteUser(String name);
    User saveLikedArticle(User user, NewsArticle article);
    User deleteLikedArticle(User user, NewsArticle article);
    User findUserIfExists(String username);
    User findUserIfExists(Long id);

}
