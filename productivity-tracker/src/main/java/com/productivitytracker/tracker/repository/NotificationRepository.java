package com.productivitytracker.tracker.repository;

import com.productivitytracker.tracker.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}