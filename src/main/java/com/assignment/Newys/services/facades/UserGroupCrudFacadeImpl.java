package com.assignment.Newys.services.facades;

import com.assignment.Newys.DTO.UserGroupDto;
import com.assignment.Newys.exceptions.DuplicateResourceEntryException;
import com.assignment.Newys.exceptions.NotFoundInDbException;
import com.assignment.Newys.models.NewsArticle;
import com.assignment.Newys.models.User;
import com.assignment.Newys.models.UserGroup;
import com.assignment.Newys.repository.NewsArticleRepository;
import com.assignment.Newys.services.AppUserService;
import com.assignment.Newys.services.NewsArticleService;
import com.assignment.Newys.services.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserGroupCrudFacadeImpl implements UserGroupCrudFacade {

    private final UserGroupService userGroupService;
    private final AppUserService appUserService;
    private final NewsArticleService newsArticleService;
    private final NewsArticleRepository newsArticleRepository;

    @Autowired
    public UserGroupCrudFacadeImpl(UserGroupService userGroupService,
                                   AppUserService appUserService,
                                   NewsArticleService newsArticleService,
                                   NewsArticleRepository newsArticleRepository) {
        this.userGroupService = userGroupService;
        this.appUserService = appUserService;
        this.newsArticleService = newsArticleService;
        this.newsArticleRepository = newsArticleRepository;
    }

    @Override
    public UserGroupDto addNewGroup(String groupName, String username) {
        User user = appUserService.findUserIfExists(username);

        try {
            UserGroup newGroup = userGroupService.createNewGroup(groupName, user);
            return UserGroupDto.convertToDto(newGroup);
        } catch (Exception e) {
            throw new DuplicateResourceEntryException("Group with that name already exists");
        }
    }

    @Override
    public void addArticleToGroup(Long groupId, Long articleId) {
        NewsArticle article = checkIfArticleExists(articleId);
        UserGroup userGroup = userGroupService.addArticle(article, groupId);
        newsArticleService.addToGroup(articleId, userGroup);
    }

    private NewsArticle checkIfArticleExists(Long id){
        Optional<NewsArticle> optionalUser = newsArticleRepository.findById(id);
        return optionalUser.orElseThrow(() ->
                new NotFoundInDbException("Article with id " + id + " does not exists"));
    }
}
