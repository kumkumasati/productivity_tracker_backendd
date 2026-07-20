package com.productivitytracker.tracker.service;

import com.productivitytracker.tracker.dto.TaskLogsDto;

import java.util.List;

public interface TaskLogsService {

    TaskLogsDto createLog(TaskLogsDto dto);

    TaskLogsDto getById(Long id);

    List<TaskLogsDto> getAllLogs();

    void delete(Long id);
}