package com.assignment.Newys.DTO;

import com.assignment.Newys.models.User;
import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private String role;

    public static User fromUserDtoToUser(UserDto userDto){
        return new User(
          userDto.username,
          userDto.password
        );
    }

}
