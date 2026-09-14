package com.studybuddy.controller;

import com.studybuddy.dto.CreateGroupRequest;
import com.studybuddy.entity.Group;
import com.studybuddy.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody CreateGroupRequest request) {
        Group createdGroup = groupService.createGroup(request);
        return ResponseEntity.ok(createdGroup);
    }
}