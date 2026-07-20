package com.productivitytracker.tracker.repository;

import com.productivitytracker.tracker.entity.TimeEntries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeEntriesRepository extends JpaRepository<TimeEntries, Long> {
}
