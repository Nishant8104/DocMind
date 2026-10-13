package com.itvedant.docmind.services;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.itvedant.docmind.dto.UserDto;
import com.itvedant.docmind.models.User;
import com.itvedant.docmind.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserDto dto) {
        String email = dto.getEmail().trim().toLowerCase();
        String username = dto.getUsername().trim();

        if (userRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "An account with this email already exists");
        }
        if (userRepository.existsByUsername(username)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This username is already in use");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setMobile(dto.getMobile());
        // Never accept roles from the registration request. All new accounts are ordinary users.
        user.setRoles(new java.util.HashSet<>(Set.of("ROLE_USER")));
        // BCrypt encodes the submitted password before the user is persisted.
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        return userRepository.save(user);
    }
}
