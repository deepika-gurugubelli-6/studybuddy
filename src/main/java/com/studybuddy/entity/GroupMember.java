package com.studybuddy.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "group_members")
@Data
public class GroupMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long groupId;

    private Long userId;

    private String role = "MEMBER";  // ADMIN or MEMBER

    @Enumerated(EnumType.STRING)
    private MemberStatus status = MemberStatus.PENDING;

    private LocalDateTime joinedAt = LocalDateTime.now();
}