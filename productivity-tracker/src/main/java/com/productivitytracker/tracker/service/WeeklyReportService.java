package com.productivitytracker.tracker.service;

import com.productivitytracker.tracker.dto.WeeklyReportDto;

import java.util.List;

public interface WeeklyReportService {

    WeeklyReportDto create(WeeklyReportDto dto);

    WeeklyReportDto getById(Long id);

    List<WeeklyReportDto> getAll();

    WeeklyReportDto update(Long id, WeeklyReportDto dto);

    void delete(Long id);
}
