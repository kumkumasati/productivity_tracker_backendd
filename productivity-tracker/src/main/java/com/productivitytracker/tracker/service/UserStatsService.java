package com.productivitytracker.tracker.service;

import com.productivitytracker.tracker.dto.UserStatsDto;

import java.util.List;

public interface UserStatsService {

    UserStatsDto create(UserStatsDto dto);

    UserStatsDto getById(Long id);

    List<UserStatsDto> getAll();

    UserStatsDto update(Long id, UserStatsDto dto);

    void delete(Long id);
}