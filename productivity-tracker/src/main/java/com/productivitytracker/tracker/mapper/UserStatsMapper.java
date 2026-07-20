package com.productivitytracker.tracker.mapper;

import com.productivitytracker.tracker.dto.UserStatsDto;
import com.productivitytracker.tracker.entity.Level;
import com.productivitytracker.tracker.entity.User;
import com.productivitytracker.tracker.entity.UserStats;

import java.time.LocalDate;

public class UserStatsMapper {

    // ✅ Entity → DTO
    public static UserStatsDto mapToDto(UserStats stats) {
        if (stats == null) return null;

        return new UserStatsDto(
                stats.getId(),
                stats.getUser() != null ? stats.getUser().getUserId() : null,
                stats.getTotalXp(),
                stats.getLevel() != null ? stats.getLevel().getLevelId() : null,
                stats.getCurrentStreak(),
                stats.getLongestStreak(),
                stats.getLastActivityDate() != null ? stats.getLastActivityDate().toString() : null
        );
    }

    // ✅ DTO → Entity (constructor-based)
    public static UserStats mapToEntity(UserStatsDto dto) {
        if (dto == null) return null;

        User user = null;
        if (dto.getUserId() != null) {
            user = new User();
            user.setUserId(dto.getUserId());
        }

        Level level = null;
        if (dto.getLevelId() != null) {
            level = new Level();
            level.setLevelId(dto.getLevelId());
        }

        return new UserStats(
                dto.getId(),
                user,
                dto.getTotalXp(),
                dto.getCurrentStreak(),
                dto.getLongestStreak(),
                dto.getLastActivityDate() != null ? LocalDate.parse(dto.getLastActivityDate()) : null,
                level
        );
    }
}
