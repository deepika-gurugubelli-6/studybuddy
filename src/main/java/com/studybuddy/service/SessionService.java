package com.studybuddy.service;

import com.studybuddy.dto.CreateSessionRequest;
import com.studybuddy.entity.Group;
import com.studybuddy.entity.StudySession;
import com.studybuddy.repository.GroupRepository;
import com.studybuddy.repository.StudySessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.studybuddy.dto.MarkAttendanceRequest;
import com.studybuddy.entity.SessionAttendance;
import com.studybuddy.repository.SessionAttendanceRepository;

import java.util.List;

@Service
public class SessionService {
    @Autowired
    private SessionAttendanceRepository attendanceRepository;

    @Autowired
    private StudySessionRepository sessionRepository;

    @Autowired
    private GroupRepository groupRepository;

    public StudySession createSession(CreateSessionRequest request) {

        Group group = groupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new RuntimeException("Group not found"));

        // Only group creator can create session
        if (!group.getCreatedBy().equals(request.getCreatedBy())) {
            throw new RuntimeException("Only group admin can create sessions");
        }

        StudySession session = new StudySession();
        session.setGroupId(request.getGroupId());
        session.setTitle(request.getTitle());
        session.setSessionTime(request.getSessionTime());
        session.setDurationInMinutes(request.getDurationInMinutes());
        session.setStatus("SCHEDULED");

        return sessionRepository.save(session);
    }

    public List<StudySession> getSessionsByGroup(Long groupId) {
        return sessionRepository.findByGroupId(groupId);
    }

    public String markAttendance(MarkAttendanceRequest request) {

        // Check if already marked
        if (attendanceRepository.existsBySessionIdAndUserId(request.getSessionId(), request.getUserId())) {
            throw new RuntimeException("Attendance already marked for this session");
        }

        SessionAttendance attendance = new SessionAttendance();
        attendance.setSessionId(request.getSessionId());
        attendance.setUserId(request.getUserId());
        attendance.setStatus(request.getStatus().toUpperCase());

        attendanceRepository.save(attendance);

        return "Attendance marked successfully as " + request.getStatus().toUpperCase();
    }
    public List<SessionAttendance> getAttendanceBySession(Long sessionId) {
        return attendanceRepository.findBySessionId(sessionId);
    }
}