package com.studybuddy.dto;

import com.studybuddy.entity.MemberStatus;
import lombok.Data;

@Data
public class HandleJoinRequest {
    private Long groupId;
    private Long userId;        // The user who requested to join
    private Long adminId;       // The group creator
    private MemberStatus status; // ACCEPTED or REJECTED
}