package com.productivitytracker.tracker.service.impl;

import com.productivitytracker.tracker.dto.NotificationDto;
import com.productivitytracker.tracker.entity.Notification;
import com.productivitytracker.tracker.entity.User;
import com.productivitytracker.tracker.exception.ResourceNotFoundException;
import com.productivitytracker.tracker.mapper.NotificationMapper;
import com.productivitytracker.tracker.repository.NotificationRepository;
import com.productivitytracker.tracker.repository.UserRepository;
import com.productivitytracker.tracker.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository notificationRepository;
    private UserRepository userRepository;

    // ✅ CREATE
    @Override
    public NotificationDto createNotification(NotificationDto dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + dto.getUserId()));

        Notification notification = NotificationMapper.mapToEntity(dto);
        notification.setUser(user); // override reference

        Notification saved = notificationRepository.save(notification);

        return NotificationMapper.mapToDto(saved);
    }

    // ✅ GET BY ID
    @Override
    public NotificationDto getById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Notification not found with id: " + id));

        return NotificationMapper.mapToDto(notification);
    }

    // ✅ GET ALL
    @Override
    public List<NotificationDto> getAll() {
        return notificationRepository.findAll()
                .stream()
                .map(NotificationMapper::mapToDto)
                .collect(Collectors.toList());
    }

    // ✅ UPDATE
    @Override
    public NotificationDto update(Long id, NotificationDto dto) {

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Notification not found with id: " + id));

        notification.setMessage(dto.getMessage());
        notification.setType(dto.getType());
        notification.setIsRead(dto.getIsRead());

        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("User not found with id: " + dto.getUserId()));

            notification.setUser(user);
        }

        Notification updated = notificationRepository.save(notification);

        return NotificationMapper.mapToDto(updated);
    }

    // ✅ DELETE
    @Override
    public void delete(Long id) {

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Notification not found with id: " + id));

        notificationRepository.delete(notification);
    }
}