package com.studybuddy.dto;

import lombok.Data;

@Data
public class MarkAttendanceRequest {
    private Long sessionId;
    private Long userId;
    private String status; // PRESENT or ABSENT
}