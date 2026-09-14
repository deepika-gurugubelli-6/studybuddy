package com.studybuddy.repository;

import com.studybuddy.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findBySubjectIgnoreCase(String subject);
    List<Group> findByCreatedBy(Long createdBy);
}