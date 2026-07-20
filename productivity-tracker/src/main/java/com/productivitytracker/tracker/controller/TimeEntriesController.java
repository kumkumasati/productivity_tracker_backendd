package com.productivitytracker.tracker.controller;

import com.productivitytracker.tracker.dto.TimeEntriesDto;
import com.productivitytracker.tracker.service.TimeEntriesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/time-entries")
@AllArgsConstructor
public class TimeEntriesController {

    private TimeEntriesService timeEntriesService;

    // ✅ CREATE
    @PostMapping
    public ResponseEntity<TimeEntriesDto> create(@RequestBody TimeEntriesDto dto) {
        TimeEntriesDto saved = timeEntriesService.create(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<TimeEntriesDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(timeEntriesService.getById(id));
    }

    // ✅ GET ALL
    @GetMapping
    public ResponseEntity<List<TimeEntriesDto>> getAll() {
        return ResponseEntity.ok(timeEntriesService.getAll());
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        timeEntriesService.delete(id);
        return ResponseEntity.ok("Time entry deleted successfully");
    }
}