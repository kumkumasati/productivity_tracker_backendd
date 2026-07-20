package com.productivitytracker.tracker.service;

import com.productivitytracker.tracker.dto.TaskCategoryMapDto;

import java.util.List;

public interface TaskCategoryMapService {

    TaskCategoryMapDto create(TaskCategoryMapDto dto);

    TaskCategoryMapDto getById(Long id);

    List<TaskCategoryMapDto> getAll();

    TaskCategoryMapDto update(Long id, TaskCategoryMapDto dto);

    void delete(Long id);
}
