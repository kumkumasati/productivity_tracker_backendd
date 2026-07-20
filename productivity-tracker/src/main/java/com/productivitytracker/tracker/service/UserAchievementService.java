package com.productivitytracker.tracker.service;

import com.productivitytracker.tracker.dto.UserAchievementDto;

import java.util.List;

public interface UserAchievementService {

    UserAchievementDto create(UserAchievementDto dto);

    UserAchievementDto getById(Long id);

    List<UserAchievementDto> getAll();

    void delete(Long id);
}
