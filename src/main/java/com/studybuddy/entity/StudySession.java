package com.studybuddy.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "study_sessions")
@Data
public class StudySession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long groupId;

    private String title;

    private LocalDateTime sessionTime;

    private int durationInMinutes;

    private String status = "SCHEDULED";  // SCHEDULED, COMPLETED, CANCELLED

    private LocalDateTime createdAt = LocalDateTime.now();
}