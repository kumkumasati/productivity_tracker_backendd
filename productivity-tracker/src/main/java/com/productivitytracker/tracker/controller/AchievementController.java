package com.productivitytracker.tracker.controller;

import com.productivitytracker.tracker.dto.AchievementDto;
import com.productivitytracker.tracker.service.AchievementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/achievements")
public class AchievementController {

    private AchievementService achievementService;

    // ✅ Create Achievement
    @PostMapping
    public ResponseEntity<AchievementDto> createAchievement(@RequestBody AchievementDto achievementDto){
        AchievementDto saved = achievementService.createAchievement(achievementDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // ✅ Get Achievement by ID
    @GetMapping("{id}")
    public ResponseEntity<AchievementDto> getAchievementById(@PathVariable("id") Long id){
        AchievementDto dto = achievementService.getAchievementById(id);
        return ResponseEntity.ok(dto);
    }

    // ✅ Get All Achievements
    @GetMapping
    public ResponseEntity<List<AchievementDto>> getAllAchievements(){
        List<AchievementDto> list = achievementService.getAllAchievements();
        return ResponseEntity.ok(list);
    }

    // ✅ Update Achievement
    @PutMapping("{id}")
    public ResponseEntity<AchievementDto> updateAchievement(
            @PathVariable("id") Long id,
            @RequestBody AchievementDto updatedAchievement){

        AchievementDto dto = achievementService.updateAchievement(id, updatedAchievement);
        return ResponseEntity.ok(dto);
    }

    // ✅ Delete Achievement
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAchievement(@PathVariable("id") Long id){
        achievementService.deleteAchievement(id);
        return ResponseEntity.ok("Achievement deleted successfully!");
    }
}