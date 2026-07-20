package com.productivitytracker.tracker.controller;

import com.productivitytracker.tracker.dto.UserAchievementDto;
import com.productivitytracker.tracker.service.UserAchievementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-achievements")
@AllArgsConstructor
public class UserAchievementController {

    private UserAchievementService service;

    // ✅ CREATE
    @PostMapping
    public ResponseEntity<UserAchievementDto> create(@RequestBody UserAchievementDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<UserAchievementDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // ✅ GET ALL
    @GetMapping
    public ResponseEntity<List<UserAchievementDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("User achievement deleted successfully");
    }
}