package com.productivitytracker.tracker.controller;

import com.productivitytracker.tracker.dto.LevelDto;
import com.productivitytracker.tracker.service.LevelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/levels")
@AllArgsConstructor
public class LevelController {

    private LevelService levelService;

    // ✅ CREATE
    @PostMapping
    public ResponseEntity<LevelDto> createLevel(@RequestBody LevelDto dto) {
        LevelDto saved = levelService.createLevel(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // ✅ GET BY ID
    @GetMapping("{id}")
    public ResponseEntity<LevelDto> getLevelById(@PathVariable Long id) {
        return ResponseEntity.ok(levelService.getLevelById(id));
    }

    // ✅ GET ALL
    @GetMapping
    public ResponseEntity<List<LevelDto>> getAllLevels() {
        return ResponseEntity.ok(levelService.getAllLevels());
    }

    // ✅ UPDATE
    @PutMapping("{id}")
    public ResponseEntity<LevelDto> updateLevel(
            @PathVariable Long id,
            @RequestBody LevelDto dto) {

        return ResponseEntity.ok(levelService.updateLevel(id, dto));
    }

    // ✅ DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteLevel(@PathVariable Long id) {
        levelService.deleteLevel(id);
        return ResponseEntity.ok("Level deleted successfully!");
    }
}
