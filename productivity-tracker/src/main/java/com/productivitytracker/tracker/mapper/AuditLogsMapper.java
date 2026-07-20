package com.productivitytracker.tracker.mapper;

import com.productivitytracker.tracker.dto.AuditLogsDto;
import com.productivitytracker.tracker.entity.AuditLogs;
import com.productivitytracker.tracker.entity.User;

import java.time.LocalDateTime;

public class AuditLogsMapper {

    // Entity → DTO
    public static AuditLogsDto mapToAuditLogsDto(AuditLogs auditLogs){
        if (auditLogs == null) return null;

        return new AuditLogsDto(
                auditLogs.getId(),
                auditLogs.getUser() != null ? auditLogs.getUser().getUserId() : null,
                auditLogs.getAction(),
                auditLogs.getMetadata(),
                auditLogs.getCreatedAt() != null ? auditLogs.getCreatedAt().toString() : null
        );
    }

    // DTO → Entity
    public static AuditLogs mapToAuditLogs(AuditLogsDto dto){
        if (dto == null) return null;

        User user = null;
        if (dto.getUserId() != null) {
            user = new User();
            user.setUserId(dto.getUserId()); // reference only
        }


        return new AuditLogs(
                dto.getId(),
                dto.getAction(),
                dto.getMetadata(),
                dto.getCreatedAt() != null ? LocalDateTime.parse(dto.getCreatedAt()) : null,
                user
        );
    }
}