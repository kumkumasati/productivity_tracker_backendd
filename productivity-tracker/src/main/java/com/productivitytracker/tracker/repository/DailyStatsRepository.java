package com.productivitytracker.tracker.repository;

import com.productivitytracker.tracker.entity.DailyStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface DailyStatsRepository extends JpaRepository<DailyStats, Long> {

    // useful because of UNIQUE(user_id, date)
    Optional<DailyStats> findByUserUserIdAndDate(Long userId, LocalDate date);
}