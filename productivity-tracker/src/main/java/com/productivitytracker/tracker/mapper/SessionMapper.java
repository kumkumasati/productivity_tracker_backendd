package com.productivitytracker.tracker.mapper;

import com.productivitytracker.tracker.dto.SessionDto;
import com.productivitytracker.tracker.entity.Session;
import com.productivitytracker.tracker.entity.User;

import java.time.LocalDateTime;

public class SessionMapper {

    // ✅ Entity → DTO
    public static SessionDto mapToDto(Session session) {
        if (session == null) return null;

        return new SessionDto(
                session.getId(),
                session.getUser() != null ? session.getUser().getUserId() : null,
                session.getToken(),
                session.getExpiresAt() != null ? session.getExpiresAt().toString() : null,
                session.getCreatedAt() != null ? session.getCreatedAt().toString() : null
        );
    }

    // ✅ DTO → Entity (constructor-based)
    public static Session mapToEntity(SessionDto dto) {
        if (dto == null) return null;

        User user = null;
        if (dto.getUserId() != null) {
            user = new User();
            user.setUserId(dto.getUserId());
        }

        return new Session(
                dto.getId(),
                user,
                dto.getToken(),
                dto.getExpiresAt() != null ? LocalDateTime.parse(dto.getExpiresAt()) : null,
                dto.getCreatedAt() != null ? LocalDateTime.parse(dto.getCreatedAt()) : null
        );
    }
}
