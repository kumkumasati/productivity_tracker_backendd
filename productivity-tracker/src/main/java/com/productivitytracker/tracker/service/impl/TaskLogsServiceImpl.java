package com.productivitytracker.tracker.service.impl;

import com.productivitytracker.tracker.dto.TaskLogsDto;
import com.productivitytracker.tracker.entity.Task;
import com.productivitytracker.tracker.entity.TaskLogs;
import com.productivitytracker.tracker.entity.User;
import com.productivitytracker.tracker.exception.ResourceNotFoundException;
import com.productivitytracker.tracker.mapper.TaskLogsMapper;
import com.productivitytracker.tracker.repository.TaskLogsRepository;
import com.productivitytracker.tracker.repository.TaskRepository;
import com.productivitytracker.tracker.repository.UserRepository;
import com.productivitytracker.tracker.service.TaskLogsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskLogsServiceImpl implements TaskLogsService {

    private TaskLogsRepository taskLogsRepository;
    private TaskRepository taskRepository;
    private UserRepository userRepository;

    @Override
    public TaskLogsDto createLog(TaskLogsDto dto) {

        // ✅ Validate Task
        Task task = taskRepository.findById(dto.getTaskId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found with id: " + dto.getTaskId()));

        // ✅ Validate User
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + dto.getUserId()));

        TaskLogs log = TaskLogsMapper.mapToEntity(dto);

        // override references
        log.setTask(task);
        log.setUser(user);

        // createdAt handled automatically, but safe fallback
        if (log.getCreatedAt() == null) {
            log.setCreatedAt(LocalDateTime.now());
        }

        TaskLogs saved = taskLogsRepository.save(log);

        return TaskLogsMapper.mapToDto(saved);
    }

    @Override
    public TaskLogsDto getById(Long id) {
        TaskLogs log = taskLogsRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("TaskLog not found with id: " + id));

        return TaskLogsMapper.mapToDto(log);
    }

    @Override
    public List<TaskLogsDto> getAllLogs() {
        return taskLogsRepository.findAll()
                .stream()
                .map(TaskLogsMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        TaskLogs log = taskLogsRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("TaskLog not found with id: " + id));

        taskLogsRepository.delete(log);
    }
}
