package com.productivitytracker.tracker.mapper;

import com.productivitytracker.tracker.dto.TimeEntriesDto;
import com.productivitytracker.tracker.entity.Task;
import com.productivitytracker.tracker.entity.TimeEntries;
import com.productivitytracker.tracker.entity.User;

import java.time.LocalDateTime;

public class TimeEntriesMapper {

    // ✅ Entity → DTO
    public static TimeEntriesDto mapToDto(TimeEntries entry) {
        if (entry == null) return null;

        return new TimeEntriesDto(
                entry.getId(),
                entry.getUser() != null ? entry.getUser().getUserId() : null,
                entry.getTask() != null ? entry.getTask().getTaskId() : null,
                entry.getDurationMinutes(),
                entry.getCreatedAt() != null ? entry.getCreatedAt().toString() : null
        );
    }

    // ✅ DTO → Entity (constructor-based)
    public static TimeEntries mapToEntity(TimeEntriesDto dto) {
        if (dto == null) return null;

        User user = null;
        if (dto.getUserId() != null) {
            user = new User();
            user.setUserId(dto.getUserId());
        }

        Task task = null;
        if (dto.getTaskId() != null) {
            task = new Task();
            task.setTaskId(dto.getTaskId());
        }

        return new TimeEntries(
                dto.getId(),
                user,
                task,
                dto.getDurationMinutes(),
                dto.getCreatedAt() != null ? LocalDateTime.parse(dto.getCreatedAt()) : null
        );
    }
}