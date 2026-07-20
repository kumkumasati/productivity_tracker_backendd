package com.productivitytracker.tracker.service;

import com.productivitytracker.tracker.dto.DailyStatsDto;

import java.util.List;

public interface DailyStatsService {

    DailyStatsDto create(DailyStatsDto dto);

    DailyStatsDto getById(Long id);

    List<DailyStatsDto> getAll();

    DailyStatsDto update(Long id, DailyStatsDto dto);

    void delete(Long id);
}