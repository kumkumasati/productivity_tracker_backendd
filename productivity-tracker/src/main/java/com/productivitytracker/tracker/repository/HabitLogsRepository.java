package com.productivitytracker.tracker.repository;

import com.productivitytracker.tracker.entity.HabitLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitLogsRepository extends JpaRepository<HabitLogs, Long> {
}