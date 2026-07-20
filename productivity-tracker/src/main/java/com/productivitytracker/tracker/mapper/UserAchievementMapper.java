package com.productivitytracker.tracker.mapper;

import com.productivitytracker.tracker.dto.UserAchievementDto;
import com.productivitytracker.tracker.entity.Achievement;
import com.productivitytracker.tracker.entity.User;
import com.productivitytracker.tracker.entity.UserAchievement;

import java.time.LocalDateTime;

public class UserAchievementMapper {

    // ✅ Entity → DTO
    public static UserAchievementDto mapToDto(UserAchievement ua) {
        if (ua == null) return null;

        return new UserAchievementDto(
                ua.getId(),
                ua.getUser() != null ? ua.getUser().getUserId() : null,
                ua.getAchievement() != null ? ua.getAchievement().getAchievementId() : null,
                ua.getEarnedAt() != null ? ua.getEarnedAt().toString() : null
        );
    }

    // ✅ DTO → Entity (constructor-based)
    public static UserAchievement mapToEntity(UserAchievementDto dto) {
        if (dto == null) return null;

        User user = null;
        if (dto.getUserId() != null) {
            user = new User();
            user.setUserId(dto.getUserId());
        }

        Achievement achievement = null;
        if (dto.getAchievementId() != null) {
            achievement = new Achievement();
            achievement.setAchievementId(dto.getAchievementId());
        }

        return new UserAchievement(
                dto.getId(),
                user,
                achievement,
                dto.getEarnedAt() != null ? LocalDateTime.parse(dto.getEarnedAt()) : null
        );
    }
}