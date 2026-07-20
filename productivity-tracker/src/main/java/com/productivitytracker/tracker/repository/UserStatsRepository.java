package com.productivitytracker.tracker.repository;

import com.productivitytracker.tracker.entity.UserStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStatsRepository extends JpaRepository<UserStats, Long> {
}