package com.productivitytracker.tracker.service.impl;

import com.productivitytracker.tracker.dto.LevelDto;
import com.productivitytracker.tracker.entity.Level;
import com.productivitytracker.tracker.exception.ResourceNotFoundException;
import com.productivitytracker.tracker.mapper.LevelMapper;
import com.productivitytracker.tracker.repository.LevelRepository;
import com.productivitytracker.tracker.service.LevelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LevelServiceImpl implements LevelService {

    private LevelRepository levelRepository;

    // ✅ CREATE
    @Override
    public LevelDto createLevel(LevelDto dto) {

        Level level = LevelMapper.mapToEntity(dto);

        Level saved = levelRepository.save(level);

        return LevelMapper.mapToDto(saved);
    }

    // ✅ GET BY ID
    @Override
    public LevelDto getLevelById(Long id) {

        Level level = levelRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Level not found with id: " + id));

        return LevelMapper.mapToDto(level);
    }

    // ✅ GET ALL
    @Override
    public List<LevelDto> getAllLevels() {

        return levelRepository.findAll()
                .stream()
                .map(LevelMapper::mapToDto)
                .collect(Collectors.toList());
    }

    // ✅ UPDATE
    @Override
    public LevelDto updateLevel(Long id, LevelDto dto) {

        Level level = levelRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Level not found with id: " + id));

        level.setLevel(dto.getLevel());
        level.setXpRequired(dto.getXpRequired());

        Level updated = levelRepository.save(level);

        return LevelMapper.mapToDto(updated);
    }

    // ✅ DELETE
    @Override
    public void deleteLevel(Long id) {

        Level level = levelRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Level not found with id: " + id));

        levelRepository.delete(level);
    }
}