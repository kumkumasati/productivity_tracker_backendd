package com.productivitytracker.tracker.controller;

import com.productivitytracker.tracker.dto.NotificationDto;
import com.productivitytracker.tracker.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@AllArgsConstructor
public class NotificationController {

    private NotificationService notificationService;

    // ✅ CREATE
    @PostMapping
    public ResponseEntity<NotificationDto> create(@RequestBody NotificationDto dto) {
        return ResponseEntity.ok(notificationService.createNotification(dto));
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<NotificationDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.getById(id));
    }

    // ✅ GET ALL
    @GetMapping
    public ResponseEntity<List<NotificationDto>> getAll() {
        return ResponseEntity.ok(notificationService.getAll());
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<NotificationDto> update(@PathVariable Long id,
                                                  @RequestBody NotificationDto dto) {
        return ResponseEntity.ok(notificationService.update(id, dto));
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        notificationService.delete(id);
        return ResponseEntity.ok("Notification deleted successfully");
    }
}