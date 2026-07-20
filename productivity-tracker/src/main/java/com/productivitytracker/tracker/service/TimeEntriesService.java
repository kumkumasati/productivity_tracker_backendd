package com.productivitytracker.tracker.service;

import com.productivitytracker.tracker.dto.TimeEntriesDto;

import java.util.List;

public interface TimeEntriesService {

    TimeEntriesDto create(TimeEntriesDto dto);

    TimeEntriesDto getById(Long id);

    List<TimeEntriesDto> getAll();

    void delete(Long id);
}