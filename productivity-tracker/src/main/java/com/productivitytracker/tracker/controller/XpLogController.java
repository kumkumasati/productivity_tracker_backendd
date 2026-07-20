package com.productivitytracker.tracker.controller;

import com.productivitytracker.tracker.dto.XpLogDto;
import com.productivitytracker.tracker.service.XpLogService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/xp-logs")
@AllArgsConstructor
public class XpLogController {

    private XpLogService service;

    @PostMapping
    public ResponseEntity<XpLogDto> create(@RequestBody XpLogDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<XpLogDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<XpLogDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}