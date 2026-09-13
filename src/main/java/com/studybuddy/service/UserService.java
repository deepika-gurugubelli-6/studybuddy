package com.studybuddy.service;

import com.studybuddy.dto.RegisterRequest;
import com.studybuddy.dto.AuthResponse;
import com.studybuddy.entity.User;
import com.studybuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // We will encrypt later
        user.setCollege(request.getCollege());

        User savedUser = userRepository.save(user);

        return new AuthResponse("User registered successfully", savedUser.getId(), savedUser.getEmail());
    }
}