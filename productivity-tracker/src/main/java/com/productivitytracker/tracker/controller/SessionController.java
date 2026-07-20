package com.productivitytracker.tracker.controller;

import com.productivitytracker.tracker.dto.SessionDto;
import com.productivitytracker.tracker.service.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
@AllArgsConstructor
public class SessionController {

    private SessionService sessionService;

    // ✅ CREATE
    @PostMapping
    public ResponseEntity<SessionDto> create(@RequestBody SessionDto dto) {
        return ResponseEntity.ok(sessionService.createSession(dto));
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<SessionDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(sessionService.getById(id));
    }

    // ✅ GET ALL
    @GetMapping
    public ResponseEntity<List<SessionDto>> getAll() {
        return ResponseEntity.ok(sessionService.getAll());
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<SessionDto> update(@PathVariable Long id,
                                             @RequestBody SessionDto dto) {
        return ResponseEntity.ok(sessionService.update(id, dto));
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        sessionService.delete(id);
        return ResponseEntity.ok("Session deleted successfully");
    }
}