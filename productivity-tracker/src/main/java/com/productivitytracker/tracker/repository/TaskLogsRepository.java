package com.productivitytracker.tracker.repository;

import com.productivitytracker.tracker.entity.TaskLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskLogsRepository extends JpaRepository<TaskLogs, Long> {

    List<TaskLogs> findByTaskTaskId(Long taskId);

    @Query("""
SELECT tl.action, tl.createdAt, t.title
FROM TaskLogs tl
JOIN tl.task t
WHERE tl.user.userId = :userId
AND tl.createdAt >= :startDate
""")
    List<Object[]> getWeeklyCompletedTasks(
            @Param("userId") Long userId,
            @Param("startDate") LocalDateTime startDate
    );
}