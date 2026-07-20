package com.productivitytracker.tracker.controller;

import com.productivitytracker.tracker.dto.XpRulesDto;
import com.productivitytracker.tracker.service.XpRulesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/xp-rules")
@AllArgsConstructor
public class XpRulesController {

    private XpRulesService service;

    // CREATE
    @PostMapping
    public ResponseEntity<XpRulesDto> create(@RequestBody XpRulesDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<XpRulesDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<XpRulesDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<XpRulesDto> update(
            @PathVariable Long id,
            @RequestBody XpRulesDto dto) {

        return ResponseEntity.ok(service.update(id, dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}