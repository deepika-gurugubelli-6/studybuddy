package com.studybuddy.controller;

import com.studybuddy.dto.CreateGroupRequest;
import com.studybuddy.entity.Group;
import com.studybuddy.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.studybuddy.dto.JoinGroupRequest;
import com.studybuddy.dto.HandleJoinRequest;
import java.util.List;

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

    @PostMapping("/join")
    public ResponseEntity<String> requestToJoin(@RequestBody JoinGroupRequest request) {
        String message = groupService.requestToJoin(request);
        return ResponseEntity.ok(message);
    }
    @PostMapping("/handle-request")
    public ResponseEntity<String> handleJoinRequest(@RequestBody HandleJoinRequest request) {
        String message = groupService.handleJoinRequest(request);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/my-groups/{userId}")
    public ResponseEntity<List<Group>> getMyGroups(@PathVariable Long userId) {
        List<Group> groups = groupService.getMyGroups(userId);
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/suggestions")
    public ResponseEntity<List<Group>> getSuggestedGroups(
            @RequestParam Long userId,
            @RequestParam String subject) {

        List<Group> suggestions = groupService.getSuggestedGroups(userId, subject);
        return ResponseEntity.ok(suggestions);
    }
}