package com.assignment.Newys.security.services;

import com.assignment.Newys.models.User;
import com.assignment.Newys.repository.UserRepository;
import com.assignment.Newys.security.dao.AppUserDao;
import com.assignment.Newys.security.models.AppUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class AppUserService implements UserDetailsService {


    private final UserRepository userRepository;

    @Autowired
    public AppUserService(AppUserDao appUserDao, UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("User with" + username + "not found"));
        return user.map(AppUserDetails::new).get();
    }
}
