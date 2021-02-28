package com.assignment.Newys.services;

import com.assignment.Newys.DTO.MessageDto;
import com.assignment.Newys.DTO.UserDto;
import com.assignment.Newys.exceptions.DuplicateResourceEntryException;
import com.assignment.Newys.exceptions.NotFoundInDbException;
import com.assignment.Newys.models.NewsArticle;
import com.assignment.Newys.models.Role;
import com.assignment.Newys.models.User;
import com.assignment.Newys.repository.RoleRepository;
import com.assignment.Newys.repository.UserRepository;
import com.assignment.Newys.security.models.AppUserDetails;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class AppUserService implements UserDetailsService, UserService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public AppUserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserIfExists(username);
        return new AppUserDetails(user);
    }

    @Override
    public void registerNewUser(UserDto userDto){
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = findRoleByName(userDto.getRole());
        user.setRoles(Sets.newHashSet(role));

        try {
            userRepository.save(user);
        } catch(Exception e) {
            throw new DuplicateResourceEntryException(
                    "User with name " + user.getUsername() + " already exists");
        }
    }

    @Override
    public MessageDto deleteUser(String name){
        int deleted = userRepository.deleteByUsername(name);
        if(deleted == 0){
            throw new NotFoundInDbException("User with name " + name + " does not exists");
        }

        return new MessageDto("User  with name " + name + " deleted successfully");
    }

    @Override
    public User saveLikedArticle(User user, NewsArticle article){
        user.addLikedArticle(article);
        return userRepository.save(user);
    }

    @Override
    public User deleteLikedArticle(User user, NewsArticle article){
        user.getLikedArticles().remove(article);
        return userRepository.save(user);
    }

    @Override
    public User findUserIfExists(String username){
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(() ->
                new UsernameNotFoundException("User with" + username + "not found"));
    }

    @Override
    public User findUserIfExists(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() ->
                new NotFoundInDbException("User with" + id + "not found"));
    }

    private Role findRoleByName(String name){
        Optional<Role> optionalRole = roleRepository.findByName(name);
        if (!optionalRole.isPresent()){
            throw new NotFoundInDbException("Role does not exist");
        }
        return  optionalRole.get();
    }

}
