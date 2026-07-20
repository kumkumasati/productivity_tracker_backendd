package com.productivitytracker.tracker.service.impl;

import com.productivitytracker.tracker.dto.TaskDto;
import com.productivitytracker.tracker.entity.Task;
import com.productivitytracker.tracker.entity.User;
import com.productivitytracker.tracker.exception.ResourceNotFoundException;
import com.productivitytracker.tracker.mapper.TaskMapper;
import com.productivitytracker.tracker.repository.TaskRepository;
import com.productivitytracker.tracker.repository.UserRepository;
import com.productivitytracker.tracker.service.TaskService;
import com.productivitytracker.tracker.entity.TaskLogs;
import com.productivitytracker.tracker.repository.TaskLogsRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private UserRepository userRepository;
    private TaskLogsRepository taskLogsRepository;

    @Override
    public TaskDto createTask(Long userId, TaskDto dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found " + userId));

        Task task = TaskMapper.mapToTask(dto);
        task.setUser(user);
        task.setCreatedAt(LocalDateTime.now());

        Task savedTask = taskRepository.save(task);

        return TaskMapper.mapToTaskDto(savedTask);
    }

    @Override
    public TaskDto getTaskById(Long taskId, Long requestingUserId) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found " + taskId));

        assertOwnership(task, requestingUserId);

        return TaskMapper.mapToTaskDto(task);
    }

    @Override
    public List<TaskDto> getTasksByUser(Long userId) {

        List<Task> tasks = taskRepository.findByUserUserId(userId);

        return tasks.stream()
                .map(TaskMapper::mapToTaskDto)
                .collect(Collectors.toList());
    }

@Override
public TaskDto updateTask(Long taskId, TaskDto dto, Long requestingUserId) {

    Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new ResourceNotFoundException("Task not found " + taskId));

    assertOwnership(task, requestingUserId);

    String previousStatus = task.getStatus();
    String newStatus = dto.getStatus();

    task.setTitle(dto.getTitle());
    task.setDescription(dto.getDescription());
    task.setStatus(newStatus);
    task.setPriority(dto.getPriority());
    task.setDueDate(dto.getDueDate());
    task.setUpdatedAt(LocalDateTime.now());

    boolean justCompleted = "COMPLETED".equalsIgnoreCase(newStatus)
            && !"COMPLETED".equalsIgnoreCase(previousStatus);
    boolean justReopened = !"COMPLETED".equalsIgnoreCase(newStatus)
            && "COMPLETED".equalsIgnoreCase(previousStatus);

    if (justCompleted) {
        task.setCompletedDate(LocalDateTime.now());
    } else if (justReopened) {
        task.setCompletedDate(null);
    }

    Task updated = taskRepository.save(task);

    if (justCompleted) {
        logTaskAction(updated, "COMPLETED");
    } else if (justReopened) {
        logTaskAction(updated, "REOPENED");
    }

    return TaskMapper.mapToTaskDto(updated);
}

    private void logTaskAction(Task task, String action) {
        TaskLogs log = new TaskLogs();
        log.setTask(task);
        log.setUser(task.getUser());
        log.setAction(action);
        log.setCreatedAt(LocalDateTime.now());
        taskLogsRepository.save(log);
    }

    @Override
    public void deleteTask(Long taskId, Long requestingUserId) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found " + taskId));

        assertOwnership(task, requestingUserId);

        taskRepository.delete(task);
    }

    // Throws 403 if the authenticated caller does not own this task,
    // instead of silently letting anyone read/edit/delete anyone's data.
    private void assertOwnership(Task task, Long requestingUserId) {
        if (task.getUser() == null || !task.getUser().getUserId().equals(requestingUserId)) {
            throw new AccessDeniedException("You do not have access to this task");
        }
    }
}
