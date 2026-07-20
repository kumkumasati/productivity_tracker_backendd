package com.productivitytracker.tracker.service;

import com.productivitytracker.tracker.dto.LevelDto;

import java.util.List;

public interface LevelService {

    LevelDto createLevel(LevelDto dto);

    LevelDto getLevelById(Long id);

    List<LevelDto> getAllLevels();

    LevelDto updateLevel(Long id, LevelDto dto);

    void deleteLevel(Long id);
}