package com.studybuddy.service;

import com.studybuddy.dto.CreateGroupRequest;
import com.studybuddy.entity.Group;
import com.studybuddy.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.studybuddy.dto.JoinGroupRequest;
import com.studybuddy.entity.GroupMember;
import com.studybuddy.entity.MemberStatus;
import com.studybuddy.repository.GroupMemberRepository;

@Service
public class GroupService {
    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @Autowired
    private GroupRepository groupRepository;

    public Group createGroup(CreateGroupRequest request) {
        Group group = new Group();
        group.setName(request.getName());
        group.setSubject(request.getSubject());
        group.setTopic(request.getTopic());
        group.setDescription(request.getDescription());
        group.setMode(request.getMode());
        group.setMaxMembers(request.getMaxMembers());
        group.setScheduledTime(request.getScheduledTime());
        group.setCreatedBy(request.getCreatedBy());
        group.setCurrentMembers(1);
        group.setStatus("OPEN");

        return groupRepository.save(group);
    }

    public String requestToJoin(JoinGroupRequest request) {

        // Check if group exists
        Group group = groupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new RuntimeException("Group not found"));

        // Check if already requested or joined
        if (groupMemberRepository.existsByGroupIdAndUserId(request.getGroupId(), request.getUserId())) {
            throw new RuntimeException("You have already requested or joined this group");
        }

        // Check if group is full
        if (group.getCurrentMembers() >= group.getMaxMembers()) {
            throw new RuntimeException("Group is already full");
        }

        // Create join request
        GroupMember member = new GroupMember();
        member.setGroupId(request.getGroupId());
        member.setUserId(request.getUserId());
        member.setRole("MEMBER");
        member.setStatus(MemberStatus.PENDING);

        groupMemberRepository.save(member);

        return "Join request sent successfully";
    }
}