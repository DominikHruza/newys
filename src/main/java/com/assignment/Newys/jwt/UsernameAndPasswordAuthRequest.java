package com.assignment.Newys.jwt;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UsernameAndPasswordAuthRequest {

    private String username;
    private String password;

}
