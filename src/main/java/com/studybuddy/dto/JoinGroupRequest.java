package com.studybuddy.dto;

import lombok.Data;

@Data
public class JoinGroupRequest {
    private Long groupId;
    private Long userId;
}