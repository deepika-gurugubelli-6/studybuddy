package com.studybuddy.repository;

import com.studybuddy.entity.SessionAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SessionAttendanceRepository extends JpaRepository<SessionAttendance, Long> {

    Optional<SessionAttendance> findBySessionIdAndUserId(Long sessionId, Long userId);

    List<SessionAttendance> findBySessionId(Long sessionId);

    boolean existsBySessionIdAndUserId(Long sessionId, Long userId);
}