package com.studybuddy.dto;

import com.studybuddy.entity.GroupMode;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CreateGroupRequest {
    private String name;
    private String subject;
    private String topic;
    private String description;
    private GroupMode mode;
    private int maxMembers;
    private LocalDateTime scheduledTime;
    private Long createdBy;  // For now we will send userId manually
}