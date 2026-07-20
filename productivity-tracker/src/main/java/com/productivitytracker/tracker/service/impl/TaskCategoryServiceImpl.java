package com.productivitytracker.tracker.service.impl;

import com.productivitytracker.tracker.dto.TaskCategoryDto;
import com.productivitytracker.tracker.entity.TaskCategory;
import com.productivitytracker.tracker.exception.ResourceNotFoundException;
import com.productivitytracker.tracker.mapper.TaskCategoryMapper;
import com.productivitytracker.tracker.repository.TaskCategoryRepository;
import com.productivitytracker.tracker.service.TaskCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskCategoryServiceImpl implements TaskCategoryService {

    private TaskCategoryRepository repository;

    // ✅ CREATE
    @Override
    public TaskCategoryDto create(TaskCategoryDto dto) {
        TaskCategory entity = TaskCategoryMapper.mapToEntity(dto);
        TaskCategory saved = repository.save(entity);
        return TaskCategoryMapper.mapToDto(saved);
    }

    // ✅ GET BY ID
    @Override
    public TaskCategoryDto getById(Long id) {
        TaskCategory category = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found with id: " + id));

        return TaskCategoryMapper.mapToDto(category);
    }

    // ✅ GET ALL
    @Override
    public List<TaskCategoryDto> getAll() {
        return repository.findAll()
                .stream()
                .map(TaskCategoryMapper::mapToDto)
                .collect(Collectors.toList());
    }

    // ✅ UPDATE
    @Override
    public TaskCategoryDto update(Long id, TaskCategoryDto dto) {

        TaskCategory category = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found with id: " + id));

        category.setName(dto.getName());

        TaskCategory updated = repository.save(category);

        return TaskCategoryMapper.mapToDto(updated);
    }

    // ✅ DELETE
    @Override
    public void delete(Long id) {

        TaskCategory category = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found with id: " + id));

        repository.delete(category);
    }
}
