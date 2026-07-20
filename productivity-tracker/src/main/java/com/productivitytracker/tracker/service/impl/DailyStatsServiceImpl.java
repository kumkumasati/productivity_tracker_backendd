package com.productivitytracker.tracker.service.impl;

import com.productivitytracker.tracker.dto.DailyStatsDto;
import com.productivitytracker.tracker.entity.DailyStats;
import com.productivitytracker.tracker.entity.User;
import com.productivitytracker.tracker.exception.ResourceNotFoundException;
import com.productivitytracker.tracker.mapper.DailyStatsMapper;
import com.productivitytracker.tracker.repository.DailyStatsRepository;
import com.productivitytracker.tracker.repository.UserRepository;
import com.productivitytracker.tracker.service.DailyStatsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DailyStatsServiceImpl implements DailyStatsService {

    private DailyStatsRepository repository;
    private UserRepository userRepository;

    // ✅ CREATE
    @Override
    public DailyStatsDto create(DailyStatsDto dto) {

        DailyStats stats = DailyStatsMapper.mapToEntity(dto);

        // validate user
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id " + dto.getUserId()));

        stats.setUser(user);

        DailyStats saved = repository.save(stats);

        return DailyStatsMapper.mapToDto(saved);
    }

    // ✅ GET BY ID
    @Override
    public DailyStatsDto getById(Long id) {

        DailyStats stats = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("DailyStats not found with id " + id));

        return DailyStatsMapper.mapToDto(stats);
    }

    // ✅ GET ALL
    @Override
    public List<DailyStatsDto> getAll() {

        return repository.findAll()
                .stream()
                .map(DailyStatsMapper::mapToDto)
                .collect(Collectors.toList());
    }

    // ✅ UPDATE
    @Override
    public DailyStatsDto update(Long id, DailyStatsDto dto) {

        DailyStats existing = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("DailyStats not found with id " + id));

        DailyStats updated = DailyStatsMapper.mapToEntity(dto);

        existing.setDate(updated.getDate());
        existing.setTasksCompleted(updated.getTasksCompleted());
        existing.setXpEarned(updated.getXpEarned());

        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("User not found with id " + dto.getUserId()));

            existing.setUser(user);
        }

        DailyStats saved = repository.save(existing);

        return DailyStatsMapper.mapToDto(saved);
    }

    // ✅ DELETE
    @Override
    public void delete(Long id) {

        DailyStats stats = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("DailyStats not found with id " + id));

        repository.deleteById(id);
    }
}