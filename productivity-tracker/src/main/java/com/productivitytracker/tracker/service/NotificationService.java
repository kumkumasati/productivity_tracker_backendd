package com.productivitytracker.tracker.service;

import com.productivitytracker.tracker.dto.NotificationDto;

import java.util.List;

public interface NotificationService {

    NotificationDto createNotification(NotificationDto dto);

    NotificationDto getById(Long id);

    List<NotificationDto> getAll();

    NotificationDto update(Long id, NotificationDto dto);

    void delete(Long id);
}