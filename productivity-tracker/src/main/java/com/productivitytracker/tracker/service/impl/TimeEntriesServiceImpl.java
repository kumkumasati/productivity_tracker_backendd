package com.productivitytracker.tracker.service.impl;

import com.productivitytracker.tracker.dto.TimeEntriesDto;
import com.productivitytracker.tracker.entity.Task;
import com.productivitytracker.tracker.entity.TimeEntries;
import com.productivitytracker.tracker.entity.User;
import com.productivitytracker.tracker.exception.ResourceNotFoundException;
import com.productivitytracker.tracker.mapper.TimeEntriesMapper;
import com.productivitytracker.tracker.repository.TaskRepository;
import com.productivitytracker.tracker.repository.TimeEntriesRepository;
import com.productivitytracker.tracker.repository.UserRepository;
import com.productivitytracker.tracker.service.TimeEntriesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TimeEntriesServiceImpl implements TimeEntriesService {

    private TimeEntriesRepository timeEntriesRepository;
    private UserRepository userRepository;
    private TaskRepository taskRepository;

    @Override
    public TimeEntriesDto create(TimeEntriesDto dto) {

        // ✅ Validate user
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + dto.getUserId()));

        // ✅ Validate task
        Task task = taskRepository.findById(dto.getTaskId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found with id: " + dto.getTaskId()));

        TimeEntries entry = TimeEntriesMapper.mapToEntity(dto);

        // override references
        entry.setUser(user);
        entry.setTask(task);

        // ✅ Auto set createdAt if not provided
        if (entry.getCreatedAt() == null) {
            entry.setCreatedAt(LocalDateTime.now());
        }

        TimeEntries saved = timeEntriesRepository.save(entry);

        return TimeEntriesMapper.mapToDto(saved);
    }

    @Override
    public TimeEntriesDto getById(Long id) {
        TimeEntries entry = timeEntriesRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Time entry not found with id: " + id));

        return TimeEntriesMapper.mapToDto(entry);
    }

    @Override
    public List<TimeEntriesDto> getAll() {
        return timeEntriesRepository.findAll()
                .stream()
                .map(TimeEntriesMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        TimeEntries entry = timeEntriesRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Time entry not found with id: " + id));

        timeEntriesRepository.delete(entry);
    }
}
