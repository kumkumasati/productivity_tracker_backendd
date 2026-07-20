package com.productivitytracker.tracker.mapper;

import com.productivitytracker.tracker.dto.TaskSessionDto;
import com.productivitytracker.tracker.entity.Task;
import com.productivitytracker.tracker.entity.TaskSession;
import com.productivitytracker.tracker.entity.User;

import java.time.LocalDateTime;

public class TaskSessionMapper {

    // ✅ Entity → DTO
    public static TaskSessionDto mapToDto(TaskSession session) {
        if (session == null) return null;

        return new TaskSessionDto(
                session.getTaskSessionId(),
                session.getStartTime() != null ? session.getStartTime().toString() : null,
                session.getEndTime() != null ? session.getEndTime().toString() : null,
                session.getDuration(),
                session.getUser() != null ? session.getUser().getUserId() : null,
                session.getTask() != null ? session.getTask().getTaskId() : null
        );
    }

    // ✅ DTO → Entity (constructor-based)
    public static TaskSession mapToEntity(TaskSessionDto dto) {
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

        return new TaskSession(
                dto.getSessionId(),
                dto.getStartTime() != null ? LocalDateTime.parse(dto.getStartTime()) : null,
                dto.getEndTime() != null ? LocalDateTime.parse(dto.getEndTime()) : null,
                dto.getDuration(),
                user,
                task
        );
    }
}

