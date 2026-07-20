package com.productivitytracker.tracker.controller;

import com.productivitytracker.tracker.dto.WeeklyReportDto;
import com.productivitytracker.tracker.service.WeeklyReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weekly-reports")
@AllArgsConstructor
public class WeeklyReportController {

    private WeeklyReportService service;

    @PostMapping
    public ResponseEntity<WeeklyReportDto> create(@RequestBody WeeklyReportDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeeklyReportDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<WeeklyReportDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<WeeklyReportDto> update(
            @PathVariable Long id,
            @RequestBody WeeklyReportDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
