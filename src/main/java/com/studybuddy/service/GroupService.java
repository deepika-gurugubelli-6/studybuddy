package com.studybuddy.service;

import com.studybuddy.dto.CreateGroupRequest;
import com.studybuddy.entity.Group;
import com.studybuddy.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

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
}