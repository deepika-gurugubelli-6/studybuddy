package com.studybuddy.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CreateSessionRequest {
    private Long groupId;
    private String title;
    private LocalDateTime sessionTime;
    private int durationInMinutes;
    private Long createdBy; // To verify admin
}