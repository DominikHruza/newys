package com.assignment.Newys.security.dao;

import com.assignment.Newys.security.models.AppUserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AppUserDaoImpl implements AppUserDao {

    @Override
    public Optional<AppUserDetails> getByUsername(String username) {
        return Optional.empty();
    }
}
