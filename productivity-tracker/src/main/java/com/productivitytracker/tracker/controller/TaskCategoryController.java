package com.productivitytracker.tracker.controller;

import com.productivitytracker.tracker.dto.TaskCategoryDto;
import com.productivitytracker.tracker.service.TaskCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task-categories")
@AllArgsConstructor
public class TaskCategoryController {

    private TaskCategoryService service;

    // ✅ CREATE
    @PostMapping
    public ResponseEntity<TaskCategoryDto> create(@RequestBody TaskCategoryDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<TaskCategoryDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // ✅ GET ALL
    @GetMapping
    public ResponseEntity<List<TaskCategoryDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<TaskCategoryDto> update(@PathVariable Long id,
                                                  @RequestBody TaskCategoryDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Category deleted successfully");
    }
}
