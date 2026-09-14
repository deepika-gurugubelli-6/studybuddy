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
import com.studybuddy.dto.HandleJoinRequest;
import java.util.List;

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

    public String handleJoinRequest(HandleJoinRequest request) {

        // 1. Check if group exists
        Group group = groupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new RuntimeException("Group not found"));

        // 2. Check if the person is the admin (creator)
        if (!group.getCreatedBy().equals(request.getAdminId())) {
            throw new RuntimeException("Only group admin can accept or reject requests");
        }

        // 3. Find the join request
        GroupMember member = groupMemberRepository
                .findByGroupIdAndUserId(request.getGroupId(), request.getUserId())
                .orElseThrow(() -> new RuntimeException("Join request not found"));

        if (member.getStatus() != MemberStatus.PENDING) {
            throw new RuntimeException("This request is already handled");
        }

        // 4. Handle Accept
        if (request.getStatus() == MemberStatus.ACCEPTED) {

            if (group.getCurrentMembers() >= group.getMaxMembers()) {
                throw new RuntimeException("Group is already full");
            }

            member.setStatus(MemberStatus.ACCEPTED);
            group.setCurrentMembers(group.getCurrentMembers() + 1);

            if (group.getCurrentMembers() >= group.getMaxMembers()) {
                group.setStatus("FULL");
            }

            groupRepository.save(group);
        }
        // 5. Handle Reject
        else if (request.getStatus() == MemberStatus.REJECTED) {
            member.setStatus(MemberStatus.REJECTED);
        } else {
            throw new RuntimeException("Invalid status. Use ACCEPTED or REJECTED");
        }

        groupMemberRepository.save(member);
        return "Request " + request.getStatus().name().toLowerCase() + " successfully";
    }

    public List<Group> getMyGroups(Long userId) {
        List<GroupMember> memberships = groupMemberRepository.findByUserId(userId);

        return memberships.stream()
                .filter(m -> m.getStatus() == MemberStatus.ACCEPTED)
                .map(m -> groupRepository.findById(m.getGroupId()).orElse(null))
                .filter(g -> g != null)
                .toList();
    }
}