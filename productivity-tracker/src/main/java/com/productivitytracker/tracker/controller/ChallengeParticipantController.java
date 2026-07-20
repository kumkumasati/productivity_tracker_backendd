package com.productivitytracker.tracker.controller;

import com.productivitytracker.tracker.dto.ChallengeParticipantDto;
import com.productivitytracker.tracker.service.ChallengeParticipantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participants")
@AllArgsConstructor
public class ChallengeParticipantController {

    private ChallengeParticipantService service;

    // CREATE
    @PostMapping
    public ResponseEntity<ChallengeParticipantDto> create(@RequestBody ChallengeParticipantDto dto) {
        return new ResponseEntity<>(service.createParticipant(dto), HttpStatus.CREATED);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ChallengeParticipantDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getParticipantById(id));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<ChallengeParticipantDto>> getAll() {
        return ResponseEntity.ok(service.getAllParticipants());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ChallengeParticipantDto> update(@PathVariable Long id,
                                                          @RequestBody ChallengeParticipantDto dto) {
        return ResponseEntity.ok(service.updateParticipant(id, dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteParticipant(id);
        return ResponseEntity.noContent().build();
    }
}