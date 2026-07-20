package com.productivitytracker.tracker.mapper;

import com.productivitytracker.tracker.dto.HabitLogsDto;
import com.productivitytracker.tracker.entity.HabitLogs;
import com.productivitytracker.tracker.entity.Habits;
import com.productivitytracker.tracker.entity.User;

import java.time.LocalDate;

public class HabitLogsMapper {

    // ✅ Entity → DTO
    public static HabitLogsDto mapToDto(HabitLogs log) {
        if (log == null) return null;

        return new HabitLogsDto(
                log.getId(),
                log.getHabit() != null ? log.getHabit().getHabitId() : null,
                log.getUser() != null ? log.getUser().getUserId() : null,
                log.getDate() != null ? log.getDate().toString() : null,
                log.getCompleted(),
                log.getCount()
        );
    }

    // ✅ DTO → Entity (constructor-based)
    public static HabitLogs mapToEntity(HabitLogsDto dto) {
        if (dto == null) return null;

        Habits habit = null;
        if (dto.getHabitId() != null) {
            habit = new Habits();
            habit.setHabitId(dto.getHabitId());
        }

        User user = null;
        if (dto.getUserId() != null) {
            user = new User();
            user.setUserId(dto.getUserId());
        }

        return new HabitLogs(
                dto.getId(),
                habit,
                user,
                dto.getDate() != null ? LocalDate.parse(dto.getDate()) : null,
                dto.getCompleted(),
                dto.getCount()
        );
    }
}