package com.productivitytracker.tracker.mapper;

import com.productivitytracker.tracker.dto.NotificationDto;
import com.productivitytracker.tracker.entity.Notification;
import com.productivitytracker.tracker.entity.User;

import java.time.LocalDateTime;

public class NotificationMapper {

    // ✅ Entity → DTO
    public static NotificationDto mapToDto(Notification notification) {
        if (notification == null) return null;

        return new NotificationDto(
                notification.getNotificationId(),
                notification.getUser() != null ? notification.getUser().getUserId() : null,
                notification.getMessage(),
                notification.getType(),
                notification.getIsRead(),
                notification.getCreatedAt() != null ? notification.getCreatedAt().toString() : null
        );
    }

    // ✅ DTO → Entity (constructor-based)
    public static Notification mapToEntity(NotificationDto dto) {
        if (dto == null) return null;

        User user = null;
        if (dto.getUserId() != null) {
            user = new User();
            user.setUserId(dto.getUserId());
        }

        return new Notification(
                dto.getId(),                       // notificationId
                user,                              // user
                dto.getMessage(),                  // message
                dto.getType(),                     // type
                dto.getIsRead(),                   // isRead
                dto.getCreatedAt() != null ? LocalDateTime.parse(dto.getCreatedAt()) : null
        );
    }
}