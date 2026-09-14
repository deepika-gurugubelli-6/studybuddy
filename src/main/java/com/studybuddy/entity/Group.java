package com.studybuddy.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "study_groups")
@Data
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String subject;

    private String topic;

    private String description;

    @Enumerated(EnumType.STRING)
    private GroupMode mode;

    private int maxMembers;

    private int currentMembers = 1;  // Creator is the first member

    private LocalDateTime scheduledTime;

    private String status = "OPEN";  // OPEN, FULL, CLOSED

    private Long createdBy;  // User ID of the creator

    private LocalDateTime createdAt = LocalDateTime.now();
}