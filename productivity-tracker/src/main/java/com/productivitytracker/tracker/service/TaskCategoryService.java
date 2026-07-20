package com.productivitytracker.tracker.service;

import com.productivitytracker.tracker.dto.TaskCategoryDto;

import java.util.List;

public interface TaskCategoryService {

    TaskCategoryDto create(TaskCategoryDto dto);

    TaskCategoryDto getById(Long id);

    List<TaskCategoryDto> getAll();

    TaskCategoryDto update(Long id, TaskCategoryDto dto);

    void delete(Long id);
}
