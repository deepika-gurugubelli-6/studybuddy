package com.studybuddy.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "session_attendance")
@Data
public class SessionAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sessionId;

    private Long userId;

    private String status = "PRESENT";  // PRESENT or ABSENT

    private LocalDateTime markedAt = LocalDateTime.now();
}