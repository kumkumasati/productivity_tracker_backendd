package com.productivitytracker.tracker.controller;

import com.productivitytracker.tracker.dto.UserStatsDto;
import com.productivitytracker.tracker.service.UserStatsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-stats")
@AllArgsConstructor
public class UserStatsController {

    private UserStatsService service;

    @PostMapping
    public ResponseEntity<UserStatsDto> create(@RequestBody UserStatsDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserStatsDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserStatsDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserStatsDto> update(
            @PathVariable Long id,
            @RequestBody UserStatsDto dto) {

        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
