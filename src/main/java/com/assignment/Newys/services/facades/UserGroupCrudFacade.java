package com.assignment.Newys.services.facades;

import com.assignment.Newys.DTO.UserGroupDto;

public interface UserGroupCrudFacade {
    UserGroupDto addNewGroup(String groupName, String username);
    void addArticleToGroup(Long groupId, Long articleId);
}
