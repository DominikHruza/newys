package com.assignment.Newys.DTO;

import com.assignment.Newys.models.UserGroup;
import lombok.Data;

@Data
public class UserGroupDto {
    private  Long groupOwner;
    private String groupName;
    private Long groupOwnerId;


    public UserGroupDto(Long groupOwner, String groupName, Long groupOwnerId) {
        this.groupOwner = groupOwner;
        this.groupName = groupName;
        this.groupOwnerId = groupOwnerId;
    }

    public static UserGroupDto convertToDto(UserGroup userGroup){
        return new UserGroupDto(
            userGroup.getId(),
            userGroup.getName(),
            userGroup.getGroupOwner().getId()
        );
    }
}
