package com.assignment.Newys.services;

import com.assignment.Newys.exceptions.NotFoundInDbException;
import com.assignment.Newys.models.NewsArticle;
import com.assignment.Newys.models.User;
import com.assignment.Newys.models.UserGroup;
import com.assignment.Newys.repository.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserGroupServiceImpl implements UserGroupService {

    private final UserGroupRepository userGroupRepository;

    @Autowired
    public UserGroupServiceImpl(UserGroupRepository userGroupRepository) {
        this.userGroupRepository = userGroupRepository;
    }

    @Override
    @Transactional
    public UserGroup createNewGroup(String groupName, User user) {
            UserGroup newGroup = new UserGroup(groupName, user);
            return userGroupRepository.save(newGroup);
    }

    @Override
    @Transactional
    public UserGroup addArticle(NewsArticle newsArticle, Long groupId) {
        UserGroup userGroup = checkIfGroupExists(groupId);
        userGroup.addArticle(newsArticle);

        return  userGroupRepository.save(userGroup);
    }

    private UserGroup checkIfGroupExists(Long id){
        Optional<UserGroup> optionalUser = userGroupRepository.findById(id);
        return optionalUser.orElseThrow(() ->
                new NotFoundInDbException("Group with name " + id + " does not exists"));
    };
}
