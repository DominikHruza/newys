package com.assignment.Newys.services;

import com.assignment.Newys.models.NewsArticle;
import com.assignment.Newys.models.User;
import com.assignment.Newys.models.UserGroup;

public interface UserGroupService {
    UserGroup createNewGroup(String groupName, User user);
    UserGroup addArticle(NewsArticle article, Long groupId);
}
