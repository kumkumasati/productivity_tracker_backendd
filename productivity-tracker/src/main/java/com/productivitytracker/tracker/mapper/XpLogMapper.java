package com.productivitytracker.tracker.mapper;

import com.productivitytracker.tracker.dto.XpLogDto;
import com.productivitytracker.tracker.entity.User;
import com.productivitytracker.tracker.entity.XpLog;

import java.time.LocalDateTime;

public class XpLogMapper {

    // ✅ Entity → DTO
    public static XpLogDto mapToDto(XpLog log) {
        if (log == null) return null;

        return new XpLogDto(
                log.getId(),
                log.getUser() != null ? log.getUser().getUserId() : null,
                log.getSource(),
                log.getAmount(),
                log.getCreatedAt() != null ? log.getCreatedAt().toString() : null
        );
    }

    // ✅ DTO → Entity (constructor-based)
    public static XpLog mapToEntity(XpLogDto dto) {
        if (dto == null) return null;

        User user = null;
        if (dto.getUserId() != null) {
            user = new User();
            user.setUserId(dto.getUserId());
        }

        return new XpLog(
                dto.getId(),
                user,
                dto.getSource(),
                dto.getAmount(),
                dto.getCreatedAt() != null ? LocalDateTime.parse(dto.getCreatedAt()) : null
        );
    }
}