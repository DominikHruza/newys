package com.assignment.Newys.DTO;

import com.assignment.Newys.models.UserGroup;
import lombok.Data;

@Data
public class UserGroupDto {
    private  Long groupId;
    private String groupName;
    private Long userId;


    public UserGroupDto(Long groupId, String groupName, Long userId) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.userId = userId;
    }

    public static UserGroupDto convertToDto(UserGroup userGroup){
        return new UserGroupDto(
            userGroup.getId(),
            userGroup.getName(),
            userGroup.getGroupOwner().getId()
        );
    }
}
