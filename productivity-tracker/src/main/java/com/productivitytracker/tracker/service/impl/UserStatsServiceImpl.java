package com.productivitytracker.tracker.service.impl;

import com.productivitytracker.tracker.dto.UserStatsDto;
import com.productivitytracker.tracker.entity.Level;
import com.productivitytracker.tracker.entity.User;
import com.productivitytracker.tracker.entity.UserStats;
import com.productivitytracker.tracker.exception.ResourceNotFoundException;
import com.productivitytracker.tracker.mapper.UserStatsMapper;
import com.productivitytracker.tracker.repository.LevelRepository;
import com.productivitytracker.tracker.repository.UserRepository;
import com.productivitytracker.tracker.repository.UserStatsRepository;
import com.productivitytracker.tracker.service.UserStatsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserStatsServiceImpl implements UserStatsService {

    private UserStatsRepository userStatsRepository;
    private UserRepository userRepository;
    private LevelRepository levelRepository;

    // ✅ CREATE
    @Override
    public UserStatsDto create(UserStatsDto dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found: " + dto.getUserId()));

        Level level = null;
        if (dto.getLevelId() != null) {
            level = levelRepository.findById(dto.getLevelId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Level not found: " + dto.getLevelId()));
        }

        UserStats stats = new UserStats();
        stats.setUser(user);
        stats.setTotalXp(dto.getTotalXp());
        stats.setCurrentStreak(dto.getCurrentStreak());
        stats.setLongestStreak(dto.getLongestStreak());

        if (dto.getLastActivityDate() != null) {
            stats.setLastActivityDate(LocalDate.parse(dto.getLastActivityDate()));
        }

        stats.setLevel(level);

        UserStats saved = userStatsRepository.save(stats);

        return UserStatsMapper.mapToDto(saved);
    }

    // ✅ GET BY ID
    @Override
    public UserStatsDto getById(Long id) {
        UserStats stats = userStatsRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("UserStats not found with id: " + id));

        return UserStatsMapper.mapToDto(stats);
    }

    // ✅ GET ALL
    @Override
    public List<UserStatsDto> getAll() {
        return userStatsRepository.findAll()
                .stream()
                .map(UserStatsMapper::mapToDto)
                .collect(Collectors.toList());
    }

    // ✅ UPDATE
    @Override
    public UserStatsDto update(Long id, UserStatsDto dto) {

        UserStats stats = userStatsRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("UserStats not found with id: " + id));

        // update simple fields
        stats.setTotalXp(dto.getTotalXp());
        stats.setCurrentStreak(dto.getCurrentStreak());
        stats.setLongestStreak(dto.getLongestStreak());

        if (dto.getLastActivityDate() != null) {
            stats.setLastActivityDate(LocalDate.parse(dto.getLastActivityDate()));
        }

        // 🔴 update LEVEL safely
        if (dto.getLevelId() != null) {
            Level level = levelRepository.findById(dto.getLevelId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Level not found with id: " + dto.getLevelId()));

            stats.setLevel(level);
        }

        // 🔴 update USER safely (optional)
        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("User not found with id: " + dto.getUserId()));

            stats.setUser(user);
        }

        UserStats updated = userStatsRepository.save(stats);

        return UserStatsMapper.mapToDto(updated);
    }

    // ✅ DELETE
    @Override
    public void delete(Long id) {

        UserStats stats = userStatsRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("UserStats not found with id: " + id));

        userStatsRepository.delete(stats);
    }
}