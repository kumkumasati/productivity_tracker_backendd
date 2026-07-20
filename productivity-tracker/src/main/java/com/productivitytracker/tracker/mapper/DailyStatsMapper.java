package com.productivitytracker.tracker.mapper;

import com.productivitytracker.tracker.dto.DailyStatsDto;
import com.productivitytracker.tracker.entity.DailyStats;
import com.productivitytracker.tracker.entity.User;

import java.time.LocalDate;

public class DailyStatsMapper {

    // ✅ Entity → DTO
    public static DailyStatsDto mapToDto(DailyStats stats) {

        if (stats == null) return null;

        return new DailyStatsDto(
                stats.getId(),
                stats.getUser() != null ? stats.getUser().getUserId() : null,
                stats.getDate() != null ? stats.getDate().toString() : null,
                stats.getTasksCompleted(),
                stats.getXpEarned()
        );
    }

    // ✅ DTO → Entity
    public static DailyStats mapToEntity(DailyStatsDto dto) {

        if (dto == null) return null;

        User user = null;

        if (dto.getUserId() != null) {
            user = new User();
            user.setUserId(dto.getUserId()); // reference only
        }

        return new DailyStats(
                dto.getId(),
                user,
                dto.getDate() != null ? LocalDate.parse(dto.getDate()) : null,
                dto.getTasksCompleted() != null ? dto.getTasksCompleted() : 0,
                dto.getXpEarned() != null ? dto.getXpEarned() : 0
        );
    }
}