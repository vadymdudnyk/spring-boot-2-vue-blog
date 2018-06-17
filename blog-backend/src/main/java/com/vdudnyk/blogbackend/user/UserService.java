package com.vdudnyk.blogbackend.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.CollectionHelper.asSet;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    User addUser(AddUserRequest addUserRequest) {
        User user = new User();
        user.setUsername(addUserRequest.getUsername());

        String encodedPassword = passwordEncoder.encode(addUserRequest.getPassword());
        user.setPassword(encodedPassword);

        Role roleUser = roleRepository.getRoleByName("ROLE_USER");
        user.setRoles(asSet(roleUser));

        return userRepository.save(user);
    }

    List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userRepository.findByUsername(currentPrincipalName);
    }
}
