package com.productivitytracker.tracker.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {
    private Long id;
    private Long userId;
    private String message;
    private String type;
    private Boolean isRead;
    private String createdAt;
}