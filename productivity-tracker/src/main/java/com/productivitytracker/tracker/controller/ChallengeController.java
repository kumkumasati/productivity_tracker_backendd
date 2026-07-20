package com.productivitytracker.tracker.controller;

import com.productivitytracker.tracker.dto.ChallengeDto;
import com.productivitytracker.tracker.service.ChallengeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {

    private ChallengeService challengeService;

    // ✅ CREATE Challenge
    @PostMapping
    public ResponseEntity<ChallengeDto> createChallenge(@RequestBody ChallengeDto challengeDto) {
        ChallengeDto saved = challengeService.createChallenge(challengeDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // ✅ GET Challenge by ID
    @GetMapping("{id}")
    public ResponseEntity<ChallengeDto> getChallengeById(@PathVariable("id") Long id) {
        ChallengeDto dto = challengeService.getChallengeById(id);
        return ResponseEntity.ok(dto);
    }

    // ✅ GET ALL Challenges
    @GetMapping
    public ResponseEntity<List<ChallengeDto>> getAllChallenges() {
        List<ChallengeDto> list = challengeService.getAllChallenges();
        return ResponseEntity.ok(list);
    }

    // ✅ UPDATE Challenge
    @PutMapping("{id}")
    public ResponseEntity<ChallengeDto> updateChallenge(
            @PathVariable("id") Long id,
            @RequestBody ChallengeDto challengeDto) {

        ChallengeDto updated = challengeService.updateChallenge(id, challengeDto);
        return ResponseEntity.ok(updated);
    }

    // ✅ DELETE Challenge
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteChallenge(@PathVariable("id") Long id) {
        challengeService.deleteChallenge(id);
        return ResponseEntity.ok("Challenge deleted successfully!");
    }
}