package com.productivitytracker.tracker.service.impl;

import com.productivitytracker.tracker.dto.TaskCategoryMapDto;
import com.productivitytracker.tracker.entity.Task;
import com.productivitytracker.tracker.entity.TaskCategory;
import com.productivitytracker.tracker.entity.TaskCategoryMap;
import com.productivitytracker.tracker.exception.ResourceNotFoundException;
import com.productivitytracker.tracker.mapper.TaskCategoryMapMapper;
import com.productivitytracker.tracker.repository.TaskCategoryMapRepository;
import com.productivitytracker.tracker.repository.TaskCategoryRepository;
import com.productivitytracker.tracker.repository.TaskRepository;
import com.productivitytracker.tracker.service.TaskCategoryMapService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskCategoryMapServiceImpl implements TaskCategoryMapService {

    private TaskCategoryMapRepository repository;
    private TaskRepository taskRepository;
    private TaskCategoryRepository categoryRepository;

    // CREATE
    @Override
    public TaskCategoryMapDto create(TaskCategoryMapDto dto) {

        Task task = taskRepository.findById(dto.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("Task not found: " + dto.getTaskId()));

        TaskCategory category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + dto.getCategoryId()));

        TaskCategoryMap entity = new TaskCategoryMap();
        entity.setTask(task);
        entity.setCategory(category);

        return TaskCategoryMapMapper.mapToDto(repository.save(entity));
    }

    // GET BY ID
    @Override
    public TaskCategoryMapDto getById(Long id) {
        TaskCategoryMap entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mapping not found: " + id));

        return TaskCategoryMapMapper.mapToDto(entity);
    }

    // GET ALL
    @Override
    public List<TaskCategoryMapDto> getAll() {
        return repository.findAll()
                .stream()
                .map(TaskCategoryMapMapper::mapToDto)
                .collect(Collectors.toList());
    }

    // UPDATE
    @Override
    public TaskCategoryMapDto update(Long id, TaskCategoryMapDto dto) {

        TaskCategoryMap entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mapping not found: " + id));

        if (dto.getTaskId() != null) {
            Task task = taskRepository.findById(dto.getTaskId())
                    .orElseThrow(() -> new ResourceNotFoundException("Task not found: " + dto.getTaskId()));
            entity.setTask(task);
        }

        if (dto.getCategoryId() != null) {
            TaskCategory category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + dto.getCategoryId()));
            entity.setCategory(category);
        }

        return TaskCategoryMapMapper.mapToDto(repository.save(entity));
    }

    // DELETE
    @Override
    public void delete(Long id) {
        TaskCategoryMap entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mapping not found: " + id));

        repository.delete(entity);
    }
}
