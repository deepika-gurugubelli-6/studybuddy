package com.studybuddy.controller;

import com.studybuddy.dto.CreateSessionRequest;
import com.studybuddy.entity.StudySession;
import com.studybuddy.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping
    public ResponseEntity<StudySession> createSession(@RequestBody CreateSessionRequest request) {
        StudySession session = sessionService.createSession(request);
        return ResponseEntity.ok(session);
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<StudySession>> getSessionsByGroup(@PathVariable Long groupId) {
        List<StudySession> sessions = sessionService.getSessionsByGroup(groupId);
        return ResponseEntity.ok(sessions);
    }
}