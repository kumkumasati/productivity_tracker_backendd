package com.productivitytracker.tracker.service.impl;

import com.productivitytracker.tracker.dto.UserAchievementDto;
import com.productivitytracker.tracker.entity.Achievement;
import com.productivitytracker.tracker.entity.User;
import com.productivitytracker.tracker.entity.UserAchievement;
import com.productivitytracker.tracker.exception.ResourceNotFoundException;
import com.productivitytracker.tracker.mapper.UserAchievementMapper;
import com.productivitytracker.tracker.repository.AchievementRepository;
import com.productivitytracker.tracker.repository.UserAchievementRepository;
import com.productivitytracker.tracker.repository.UserRepository;
import com.productivitytracker.tracker.service.UserAchievementService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserAchievementServiceImpl implements UserAchievementService {

    private UserAchievementRepository repository;
    private UserRepository userRepository;
    private AchievementRepository achievementRepository;

    @Override
    public UserAchievementDto create(UserAchievementDto dto) {

        // ✅ Validate User
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + dto.getUserId()));

        // ✅ Validate Achievement
        Achievement achievement = achievementRepository.findById(dto.getAchievementId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Achievement not found with id: " + dto.getAchievementId()));

        // 🔒 Prevent duplicate (important because of unique constraint)
        repository.findByUser_UserIdAndAchievement_AchievementId(
                dto.getUserId(),
                dto.getAchievementId()
        ).ifPresent(existing -> {
            throw new RuntimeException("User already has this achievement");
        });

        UserAchievement entity = UserAchievementMapper.mapToEntity(dto);

        entity.setUser(user);
        entity.setAchievement(achievement);

        if (entity.getEarnedAt() == null) {
            entity.setEarnedAt(LocalDateTime.now());
        }

        UserAchievement saved = repository.save(entity);

        return UserAchievementMapper.mapToDto(saved);
    }

    @Override
    public UserAchievementDto getById(Long id) {
        UserAchievement ua = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("UserAchievement not found with id: " + id));

        return UserAchievementMapper.mapToDto(ua);
    }

    @Override
    public List<UserAchievementDto> getAll() {
        return repository.findAll()
                .stream()
                .map(UserAchievementMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        UserAchievement ua = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("UserAchievement not found with id: " + id));

        repository.delete(ua);
    }
}