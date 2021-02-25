package com.assignment.Newys.DTO;

import com.assignment.Newys.models.User;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String role;


    public UserDto(Long id, String username) {
        this.id = id;
        this.username = username;

    }

    public static UserDto convertToDto(User user){
        return new UserDto(
                user.getId(),
                user.getUsername()
        );
    }
}
