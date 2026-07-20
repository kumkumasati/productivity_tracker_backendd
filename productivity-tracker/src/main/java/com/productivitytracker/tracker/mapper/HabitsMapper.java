package com.productivitytracker.tracker.mapper;

import com.productivitytracker.tracker.dto.HabitsDto;
import com.productivitytracker.tracker.entity.Habits;
import com.productivitytracker.tracker.entity.User;

import java.time.LocalDateTime;

public class HabitsMapper {

    // ✅ Entity → DTO
    public static HabitsDto mapToHabitsDto(Habits habit) {
        if (habit == null) return null;

        return new HabitsDto(
                habit.getHabitId(),
                habit.getUser() != null ? habit.getUser().getUserId() : null,
                habit.getHabitName(),
                habit.getFrequency(),
                habit.getTargetCount()
        );
    }

    // ✅ DTO → Entity (constructor-based)
    public static Habits mapToHabits(HabitsDto dto) {
        if (dto == null) return null;

        // Create user reference (only ID)
        User user = null;
        if (dto.getUserId() != null) {
            user = new User();
            user.setUserId(dto.getUserId());
        }

        return new Habits(
                dto.getId(),                 // habitId
                dto.getName(),              // habitName
                dto.getFrequency(),         // frequency
                dto.getTargetCount(),       // ✅ FIXED
                LocalDateTime.now(),        // createdAt
                user                        // user
        );
    }
}