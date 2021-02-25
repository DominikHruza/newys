package com.assignment.Newys.bootstrap;

import com.assignment.Newys.models.Role;
import com.assignment.Newys.models.User;
import com.assignment.Newys.repository.RoleRepository;
import com.assignment.Newys.repository.UserRepository;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    Logger logger = LoggerFactory.getLogger(DataInitializer.class);
    private boolean initDone = false;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public DataInitializer(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(initDone){
            logger.info("Data already initialised. Exited init process.");
            return;
        }

        final Role adminRole = createRoleIfNotFound("ADMIN");
        final Role readerRole = createRoleIfNotFound("READER");
        final Role authorRole = createRoleIfNotFound("AUTHOR");

        createUserIfNotFound(
                "admin",
                "$2y$10$/XVvRiYKC7S61Y.HbGdScuWyBi6/FdDh34kRAGtIEiw7QjzKLaMea",
                Sets.newHashSet(adminRole)
        );

        createUserIfNotFound(
                "reader",
                "$2y$10$dNQus9y6XpQv9uxf6PGtFO5Ek9Q52PWA556P7mMv9UI1qwG8x3q/.",
                Sets.newHashSet(readerRole)
        );

        createUserIfNotFound(
                "author",
                "$2y$10$RK9gfqZH/Ad1imlyr7BLdOvEiQTdYdSPsHpbxb/v7BKMbid51jYVe",
                Sets.newHashSet(authorRole)
        );

        initDone = true;
        logger.info("Data init process done.");
    }

    @Transactional
    public Role createRoleIfNotFound(final String name) {
        Optional<Role> role = roleRepository.findByName(name);
        Role newRole;
        if (!role.isPresent()) {
            newRole = new Role(name);
            roleRepository.save(newRole);
            logger.info("New role: " + name + " created.");
            return newRole;
        } else {
            return role.get();
        }
    }

    @Transactional
    public User createUserIfNotFound(final String username, final String password, final Set<Role> roles) {
        Optional<User> user = userRepository.findByUsername(username);
        User newUser;
        if (!user.isPresent()) {
            newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setRoles(roles);
            userRepository.save(newUser);
            logger.info("User with username: " + username + " initialised.");
            return newUser;
        } else {
            return user.get();
        }
    }
}
