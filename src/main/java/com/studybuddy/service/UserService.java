package com.studybuddy.service;

import com.studybuddy.dto.RegisterRequest;
import com.studybuddy.dto.AuthResponse;
import com.studybuddy.entity.User;
import com.studybuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.studybuddy.dto.LoginRequest;
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

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return new AuthResponse("Login successful", user.getId(), user.getEmail());
    }
}