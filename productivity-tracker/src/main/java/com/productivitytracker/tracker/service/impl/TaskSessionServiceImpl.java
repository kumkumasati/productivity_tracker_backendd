package com.productivitytracker.tracker.service.impl;

import com.productivitytracker.tracker.dto.TaskSessionDto;
import com.productivitytracker.tracker.entity.Task;
import com.productivitytracker.tracker.entity.TaskSession;
import com.productivitytracker.tracker.entity.User;
import com.productivitytracker.tracker.exception.ResourceNotFoundException;
import com.productivitytracker.tracker.mapper.TaskSessionMapper;
import com.productivitytracker.tracker.repository.TaskRepository;
import com.productivitytracker.tracker.repository.TaskSessionRepository;
import com.productivitytracker.tracker.repository.UserRepository;
import com.productivitytracker.tracker.service.TaskSessionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskSessionServiceImpl implements TaskSessionService {

    private TaskSessionRepository taskSessionRepository;
    private UserRepository userRepository;
    private TaskRepository taskRepository;

    @Override
    public TaskSessionDto createSession(TaskSessionDto dto) {

        // ✅ Validate User
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + dto.getUserId()));

        // ✅ Validate Task
        Task task = taskRepository.findById(dto.getTaskId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found with id: " + dto.getTaskId()));

        TaskSession session = TaskSessionMapper.mapToEntity(dto);

        // override references
        session.setUser(user);
        session.setTask(task);

        // 🔥 Auto-calculate duration if start & end exist
        if (session.getStartTime() != null && session.getEndTime() != null) {
            long seconds = java.time.Duration.between(
                    session.getStartTime(),
                    session.getEndTime()
            ).getSeconds();

            session.setDuration(seconds);
        }

        TaskSession saved = taskSessionRepository.save(session);

        return TaskSessionMapper.mapToDto(saved);
    }

    @Override
    public TaskSessionDto getById(Long id) {
        TaskSession session = taskSessionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Session not found with id: " + id));

        return TaskSessionMapper.mapToDto(session);
    }

    @Override
    public List<TaskSessionDto> getAll() {
        return taskSessionRepository.findAll()
                .stream()
                .map(TaskSessionMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        TaskSession session = taskSessionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Session not found with id: " + id));

        taskSessionRepository.delete(session);
    }
}
