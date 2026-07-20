package com.productivitytracker.tracker.service;

import com.productivitytracker.tracker.dto.TaskSessionDto;

import java.util.List;

public interface TaskSessionService {

    TaskSessionDto createSession(TaskSessionDto dto);

    TaskSessionDto getById(Long id);

    List<TaskSessionDto> getAll();

    void delete(Long id);
}