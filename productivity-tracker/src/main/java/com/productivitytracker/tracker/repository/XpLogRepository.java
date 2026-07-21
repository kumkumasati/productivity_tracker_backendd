package com.productivitytracker.tracker.repository;

import com.productivitytracker.tracker.entity.XpLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface XpLogRepository extends JpaRepository<XpLog, Long> {
    List<XpLog> findByUser_UserIdAndCreatedAtAfter(Long userId, LocalDateTime after);
}