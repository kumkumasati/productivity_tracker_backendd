package com.productivitytracker.tracker.mapper;

import com.productivitytracker.tracker.dto.LevelDto;
import com.productivitytracker.tracker.entity.Level;

public class LevelMapper {

    // ✅ Entity → DTO
    public static LevelDto mapToDto(Level level) {
        if (level == null) return null;

        return new LevelDto(
                level.getLevelId(),
                level.getLevel(),
                level.getXpRequired()
        );
    }

    // ✅ DTO → Entity (constructor-based)
    public static Level mapToEntity(LevelDto dto) {
        if (dto == null) return null;

        return new Level(
                dto.getId(),           // levelId
                dto.getLevel(),        // level
                dto.getXpRequired(),   // requiredXp
                null                  // users (ignored)
        );
    }
}