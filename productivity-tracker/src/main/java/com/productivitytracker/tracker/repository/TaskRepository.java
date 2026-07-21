package com.productivitytracker.tracker.repository;

import com.productivitytracker.tracker.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    //  Get all tasks for a user
    List<Task> findByUserUserId(Long userId);

    // Used to check completed-task-count achievement thresholds
    long countByUserUserIdAndStatusIgnoreCase(Long userId, String status);
}