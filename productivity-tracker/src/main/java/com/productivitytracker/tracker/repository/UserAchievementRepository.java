package com.productivitytracker.tracker.repository;

import com.productivitytracker.tracker.entity.UserAchievement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAchievementRepository extends JpaRepository<UserAchievement, Long> {

    Optional<UserAchievement> findByUser_UserIdAndAchievement_AchievementId(Long userId, Long achievementId);
}