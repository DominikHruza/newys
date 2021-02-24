package com.assignment.Newys.security.dao;

import com.assignment.Newys.security.models.AppUserDetails;

import java.util.Optional;

public interface AppUserDao {
    public Optional<AppUserDetails> getByUsername(String username);
}
