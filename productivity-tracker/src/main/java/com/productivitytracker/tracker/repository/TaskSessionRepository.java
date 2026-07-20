package com.productivitytracker.tracker.repository;

import com.productivitytracker.tracker.entity.TaskSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskSessionRepository extends JpaRepository<TaskSession, Long> {
}
