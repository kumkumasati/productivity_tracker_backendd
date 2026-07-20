package com.productivitytracker.tracker.controller;

import com.productivitytracker.tracker.dto.TaskSessionDto;
import com.productivitytracker.tracker.service.TaskSessionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task-sessions")
@AllArgsConstructor
public class TaskSessionController {

    private TaskSessionService taskSessionService;

    // ✅ CREATE
    @PostMapping
    public ResponseEntity<TaskSessionDto> create(@RequestBody TaskSessionDto dto) {
        TaskSessionDto saved = taskSessionService.createSession(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<TaskSessionDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(taskSessionService.getById(id));
    }

    // ✅ GET ALL
    @GetMapping
    public ResponseEntity<List<TaskSessionDto>> getAll() {
        return ResponseEntity.ok(taskSessionService.getAll());
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        taskSessionService.delete(id);
        return ResponseEntity.ok("Session deleted successfully");
    }
}