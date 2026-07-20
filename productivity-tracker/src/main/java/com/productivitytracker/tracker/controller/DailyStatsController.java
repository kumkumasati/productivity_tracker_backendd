package com.productivitytracker.tracker.controller;

import com.productivitytracker.tracker.dto.DailyStatsDto;
import com.productivitytracker.tracker.service.DailyStatsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/daily-stats")
@AllArgsConstructor
public class DailyStatsController {

    private DailyStatsService service;

    // CREATE
    @PostMapping
    public ResponseEntity<DailyStatsDto> create(@RequestBody DailyStatsDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<DailyStatsDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<DailyStatsDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<DailyStatsDto> update(
            @PathVariable Long id,
            @RequestBody DailyStatsDto dto) {

        return ResponseEntity.ok(service.update(id, dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("DailyStats deleted successfully");
    }
}