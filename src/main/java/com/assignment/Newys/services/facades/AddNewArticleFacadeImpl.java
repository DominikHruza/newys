package com.assignment.Newys.services.facades;

import com.assignment.Newys.DTO.ArticleDto;
import com.assignment.Newys.exceptions.NotFoundInDbException;
import com.assignment.Newys.models.User;
import com.assignment.Newys.repository.RoleRepository;
import com.assignment.Newys.repository.UserRepository;
import com.assignment.Newys.services.NewsArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddNewArticleFacadeImpl implements AddNewArticleFacade {

    private final NewsArticleService newsArticleService;
    private final UserRepository userRepository;
    @Autowired
    public AddNewArticleFacadeImpl(NewsArticleService newsArticleService,
                                   UserRepository userRepository, RoleRepository roleRepository) {
        this.newsArticleService = newsArticleService;
        this.userRepository = userRepository;
    }

    @Override
    public void AddNewsArticle(ArticleDto articleDto) {
        String username = articleDto.getUsername();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(!optionalUser.isPresent()){
            throw new NotFoundInDbException("User with username " + username + " does not exists");
        }

        User user = optionalUser.get();
        newsArticleService.addNewArticle(articleDto, user);
    }
}
