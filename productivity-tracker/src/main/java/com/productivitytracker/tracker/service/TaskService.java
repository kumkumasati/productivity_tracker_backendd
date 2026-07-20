package com.productivitytracker.tracker.service;

import com.productivitytracker.tracker.dto.TaskDto;

import java.util.List;

public interface TaskService {

    TaskDto createTask(Long userId, TaskDto dto);

    // requestingUserId = the authenticated caller, used to enforce ownership
    TaskDto getTaskById(Long taskId, Long requestingUserId);

    List<TaskDto> getTasksByUser(Long userId);

    TaskDto updateTask(Long taskId, TaskDto dto, Long requestingUserId);

    void deleteTask(Long taskId, Long requestingUserId);
}
