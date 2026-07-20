package com.productivitytracker.tracker.mapper;

import com.productivitytracker.tracker.dto.AchievementDto;
import com.productivitytracker.tracker.entity.Achievement;

public class AchievementMapper {

    // Entity → DTO
    public static AchievementDto mapToAchievementDto(Achievement achievement){
        if (achievement == null) return null;

        return new AchievementDto(
                achievement.getAchievementId(),
                achievement.getTitle(),
                achievement.getDescription(),
                achievement.getXpReward()
        );
    }

    // DTO → Entity
    public static Achievement mapToAchievement(AchievementDto dto){
        if (dto == null) return null;

        return new Achievement(
                dto.getAchievementId(),
                dto.getTitle(),
                dto.getDescription(),
                dto.getXpReward()
        );
    }
}
