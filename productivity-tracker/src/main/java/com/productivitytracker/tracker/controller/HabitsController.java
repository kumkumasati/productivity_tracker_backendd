package com.productivitytracker.tracker.controller;

import com.productivitytracker.tracker.dto.HabitsDto;
import com.productivitytracker.tracker.service.HabitsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
@AllArgsConstructor
public class HabitsController {

    private HabitsService habitsService;

    // ✅ CREATE
    @PostMapping
    public ResponseEntity<HabitsDto> createHabit(@RequestBody HabitsDto dto) {
        HabitsDto saved = habitsService.createHabit(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // ✅ GET BY ID
    @GetMapping("{id}")
    public ResponseEntity<HabitsDto> getHabitById(@PathVariable Long id) {
        return ResponseEntity.ok(habitsService.getHabitById(id));
    }

    // ✅ GET ALL
    @GetMapping
    public ResponseEntity<List<HabitsDto>> getAllHabits() {
        return ResponseEntity.ok(habitsService.getAllHabits());
    }

    // ✅ UPDATE
    @PutMapping("{id}")
    public ResponseEntity<HabitsDto> updateHabit(
            @PathVariable Long id,
            @RequestBody HabitsDto dto) {

        return ResponseEntity.ok(habitsService.updateHabit(id, dto));
    }

    // ✅ DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteHabit(@PathVariable Long id) {
        habitsService.deleteHabit(id);
        return ResponseEntity.ok("Habit deleted successfully!");
    }
}