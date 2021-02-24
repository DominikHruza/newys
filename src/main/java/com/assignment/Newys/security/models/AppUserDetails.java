package com.assignment.Newys.security.models;

import com.assignment.Newys.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class AppUserDetails implements UserDetails {

    private final List<? extends GrantedAuthority> grantedAuthorities;
    private final String password;
    private final String username;
    private final boolean isEnabled;

    public AppUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.isEnabled = user.isActive();
        this.grantedAuthorities = Arrays.stream(user.getRoles()
                .toString()
                .toUpperCase()
                .split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
