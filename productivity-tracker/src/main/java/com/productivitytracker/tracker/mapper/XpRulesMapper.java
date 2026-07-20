package com.productivitytracker.tracker.mapper;

import com.productivitytracker.tracker.dto.XpRulesDto;
import com.productivitytracker.tracker.entity.XpRules;

public class XpRulesMapper {

    // ✅ Entity → DTO
    public static XpRulesDto mapToDto(XpRules entity) {
        if (entity == null) return null;

        return new XpRulesDto(
                entity.getId(),
                entity.getAction(),
                entity.getXpValue()
        );
    }

    // ✅ DTO → Entity (constructor-based)
    public static XpRules mapToEntity(XpRulesDto dto) {
        if (dto == null) return null;

        return new XpRules(
                dto.getId(),
                dto.getAction(),
                dto.getXpValue()
        );
    }
}