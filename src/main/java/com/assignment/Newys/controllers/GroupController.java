package com.assignment.Newys.controllers;

import com.assignment.Newys.DTO.UserGroupDto;
import com.assignment.Newys.services.facades.UserGroupCrudFacade;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/group")
public class GroupController {

    private final UserGroupCrudFacade userGroupCrudFacade;

    public GroupController(UserGroupCrudFacade userGroupCrudFacade) {
        this.userGroupCrudFacade = userGroupCrudFacade;
    }


    @PostMapping(value = "/{groupName}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserGroupDto createGroup(Principal principal,
                                    @PathVariable String groupName){
        UserGroupDto userGroupDto = userGroupCrudFacade.addNewGroup(groupName, principal.getName());
        return userGroupDto;
    }


    @PostMapping(value = "/{groupId}/{articleId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addArticleToGroup( @PathVariable Long groupId,
                            @PathVariable Long articleId){
        userGroupCrudFacade.addArticleToGroup(groupId, articleId);
    }


}
