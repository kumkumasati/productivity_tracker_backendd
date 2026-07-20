package com.productivitytracker.tracker.service.impl;

import com.productivitytracker.tracker.dto.AchievementDto;
import com.productivitytracker.tracker.entity.Achievement;
import com.productivitytracker.tracker.exception.ResourceNotFoundException;
import com.productivitytracker.tracker.mapper.AchievementMapper;
import com.productivitytracker.tracker.repository.AchievementRepository;
import com.productivitytracker.tracker.service.AchievementService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AchievementServiceImpl implements AchievementService {

    private AchievementRepository achievementRepository;

    // ✅ Create Achievement
    @Override
    public AchievementDto createAchievement(AchievementDto achievementDto) {
        Achievement achievement = AchievementMapper.mapToAchievement(achievementDto);
        Achievement saved = achievementRepository.save(achievement);
        return AchievementMapper.mapToAchievementDto(saved);
    }

    // ✅ Get Achievement by ID
    @Override
    public AchievementDto getAchievementById(Long id) {
        Achievement achievement = achievementRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Achievement does not exist with id: " + id));

        return AchievementMapper.mapToAchievementDto(achievement);
    }

    // ✅ Get All Achievements
    @Override
    public List<AchievementDto> getAllAchievements() {
        List<Achievement> list = achievementRepository.findAll();

        return list.stream()
                .map(AchievementMapper::mapToAchievementDto)
                .collect(Collectors.toList());
    }

    // ✅ Update Achievement
    @Override
    public AchievementDto updateAchievement(Long id, AchievementDto updatedDto) {

        Achievement achievement = achievementRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Achievement does not exist with id: " + id));

        achievement.setTitle(updatedDto.getTitle());
        achievement.setDescription(updatedDto.getDescription());
        achievement.setXpReward(updatedDto.getXpReward());

        Achievement updated = achievementRepository.save(achievement);

        return AchievementMapper.mapToAchievementDto(updated);
    }

    // ✅ Delete Achievement
    @Override
    public void deleteAchievement(Long id) {
        Achievement achievement = achievementRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Achievement does not exist with id: " + id));

        achievementRepository.deleteById(id);
    }
}
