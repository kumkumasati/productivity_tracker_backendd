package com.productivitytracker.tracker.repository;

import com.productivitytracker.tracker.entity.UserStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserStatsRepository extends JpaRepository<UserStats, Long> {

    Optional<UserStats> findByUser_UserId(Long userId);
}