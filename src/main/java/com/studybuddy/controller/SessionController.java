package com.studybuddy.controller;

import com.studybuddy.dto.CreateSessionRequest;
import com.studybuddy.entity.StudySession;
import com.studybuddy.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.studybuddy.dto.MarkAttendanceRequest;
import com.studybuddy.entity.SessionAttendance;

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

    @PostMapping("/attendance")
    public ResponseEntity<String> markAttendance(@RequestBody MarkAttendanceRequest request) {
        String message = sessionService.markAttendance(request);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{sessionId}/attendance")
    public ResponseEntity<List<SessionAttendance>> getAttendance(@PathVariable Long sessionId) {
        List<SessionAttendance> attendanceList = sessionService.getAttendanceBySession(sessionId);
        return ResponseEntity.ok(attendanceList);
    }
}