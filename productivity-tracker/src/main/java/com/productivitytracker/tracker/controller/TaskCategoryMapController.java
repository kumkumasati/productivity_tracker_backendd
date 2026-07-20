package com.productivitytracker.tracker.controller;

import com.productivitytracker.tracker.dto.TaskCategoryMapDto;
import com.productivitytracker.tracker.service.TaskCategoryMapService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task-category-map")
@AllArgsConstructor
public class TaskCategoryMapController {

    private TaskCategoryMapService service;

    // CREATE
    @PostMapping
    public ResponseEntity<TaskCategoryMapDto> create(@RequestBody TaskCategoryMapDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<TaskCategoryMapDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<TaskCategoryMapDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<TaskCategoryMapDto> update(@PathVariable Long id,
                                                     @RequestBody TaskCategoryMapDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Mapping deleted successfully");
    }
}