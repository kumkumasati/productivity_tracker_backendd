package com.productivitytracker.tracker.service;

import com.productivitytracker.tracker.dto.HabitLogsDto;

import java.util.List;

public interface HabitLogsService {

    HabitLogsDto create(HabitLogsDto dto);

    HabitLogsDto getById(Long id);

    List<HabitLogsDto> getAll();

    HabitLogsDto update(Long id, HabitLogsDto dto);

    void delete(Long id);
}