package com.productivitytracker.tracker.controller;

import com.productivitytracker.tracker.dto.HabitLogsDto;
import com.productivitytracker.tracker.service.HabitLogsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habit-logs")
@AllArgsConstructor
public class HabitLogsController {

    private HabitLogsService habitLogsService;

    @PostMapping
    public ResponseEntity<HabitLogsDto> create(@RequestBody HabitLogsDto dto) {
        return ResponseEntity.ok(habitLogsService.create(dto));
    }

    @GetMapping("{id}")
    public ResponseEntity<HabitLogsDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(habitLogsService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<HabitLogsDto>> getAll() {
        return ResponseEntity.ok(habitLogsService.getAll());
    }

    @PutMapping("{id}")
    public ResponseEntity<HabitLogsDto> update(@PathVariable Long id,
                                               @RequestBody HabitLogsDto dto) {
        return ResponseEntity.ok(habitLogsService.update(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        habitLogsService.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
