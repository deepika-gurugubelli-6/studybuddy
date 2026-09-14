package com.studybuddy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of(
                "project", "StudyBuddy - Smart Study Group Matcher",
                "status", "Running successfully",
                "message", "Welcome to StudyBuddy Backend API"
        );
    }
}