package com.studybuddy.repository;

import com.studybuddy.entity.StudySession;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudySessionRepository extends JpaRepository<StudySession, Long> {
    List<StudySession> findByGroupId(Long groupId);
}