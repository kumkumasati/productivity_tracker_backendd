package com.productivitytracker.tracker.mapper;

import com.productivitytracker.tracker.dto.TaskLogsDto;
import com.productivitytracker.tracker.entity.Task;
import com.productivitytracker.tracker.entity.TaskLogs;
import com.productivitytracker.tracker.entity.User;

import java.time.LocalDateTime;

public class TaskLogsMapper {

    // ✅ Entity → DTO
    public static TaskLogsDto mapToDto(TaskLogs log) {
        if (log == null) return null;

        return new TaskLogsDto(
                log.getTaskLogsId(),
                log.getTask() != null ? log.getTask().getTaskId() : null,
                log.getUser() != null ? log.getUser().getUserId() : null,
                log.getAction(),
                log.getCreatedAt() != null ? log.getCreatedAt().toString() : null
        );
    }

    // ✅ DTO → Entity (constructor-based)
    public static TaskLogs mapToEntity(TaskLogsDto dto) {
        if (dto == null) return null;

        Task task = null;
        if (dto.getTaskId() != null) {
            task = new Task();
            task.setTaskId(dto.getTaskId());
        }

        User user = null;
        if (dto.getUserId() != null) {
            user = new User();
            user.setUserId(dto.getUserId());
        }

        return new TaskLogs(
                dto.getId(),
                dto.getAction(),
                dto.getCreatedAt() != null ? LocalDateTime.parse(dto.getCreatedAt()) : null,
                task,
                user
        );
    }
}
