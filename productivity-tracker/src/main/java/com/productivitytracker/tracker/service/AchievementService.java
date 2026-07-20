package com.productivitytracker.tracker.service;

import com.productivitytracker.tracker.dto.AchievementDto;

import java.util.List;

public interface AchievementService {

    // Create Achievement
    AchievementDto createAchievement(AchievementDto achievementDto);

    // Get Achievement by ID
    AchievementDto getAchievementById(Long id);

    // Get All Achievements
    List<AchievementDto> getAllAchievements();

    // Update Achievement
    AchievementDto updateAchievement(Long id, AchievementDto updatedAchievement);

    // Delete Achievement
    void deleteAchievement(Long id);
}