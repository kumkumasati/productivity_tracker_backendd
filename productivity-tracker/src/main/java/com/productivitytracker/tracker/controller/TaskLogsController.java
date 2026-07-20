package com.productivitytracker.tracker.controller;

import com.productivitytracker.tracker.dto.TaskLogsDto;
import com.productivitytracker.tracker.service.TaskLogsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task-logs")
@AllArgsConstructor
public class TaskLogsController {

    private TaskLogsService taskLogsService;

    // ✅ CREATE
    @PostMapping
    public ResponseEntity<TaskLogsDto> createLog(@RequestBody TaskLogsDto dto) {
        TaskLogsDto saved = taskLogsService.createLog(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<TaskLogsDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(taskLogsService.getById(id));
    }

    // ✅ GET ALL
    @GetMapping
    public ResponseEntity<List<TaskLogsDto>> getAll() {
        return ResponseEntity.ok(taskLogsService.getAllLogs());
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        taskLogsService.delete(id);
        return ResponseEntity.ok("Task log deleted successfully");
    }
}
