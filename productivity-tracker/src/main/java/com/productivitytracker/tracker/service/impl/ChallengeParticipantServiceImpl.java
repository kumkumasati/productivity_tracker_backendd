package com.productivitytracker.tracker.service.impl;

import com.productivitytracker.tracker.dto.ChallengeParticipantDto;
import com.productivitytracker.tracker.entity.Challenge;
import com.productivitytracker.tracker.entity.ChallengeParticipant;
import com.productivitytracker.tracker.entity.User;
import com.productivitytracker.tracker.exception.ResourceNotFoundException;
import com.productivitytracker.tracker.mapper.ChallengeParticipantMapper;
import com.productivitytracker.tracker.repository.ChallengeParticipantRepository;
import com.productivitytracker.tracker.repository.ChallengeRepository;
import com.productivitytracker.tracker.repository.UserRepository;
import com.productivitytracker.tracker.service.ChallengeParticipantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChallengeParticipantServiceImpl implements ChallengeParticipantService {

    private ChallengeParticipantRepository repository;
    private ChallengeRepository challengeRepository;
    private UserRepository userRepository;

    // ✅ CREATE (FIXED)
    @Override
    public ChallengeParticipantDto createParticipant(ChallengeParticipantDto dto) {

        // 🔴 Prevent duplicate
        if (repository.existsByChallengeIdAndUserUserId(dto.getChallengeId(), dto.getUserId())) {
            throw new RuntimeException("User already joined this challenge");
        }

        ChallengeParticipant entity = ChallengeParticipantMapper.mapToEntity(dto);

        Challenge challenge = challengeRepository.findById(dto.getChallengeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Challenge not found with id " + dto.getChallengeId()));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id " + dto.getUserId()));

        entity.setChallenge(challenge);
        entity.setUser(user);

        ChallengeParticipant saved = repository.save(entity);

        return ChallengeParticipantMapper.mapToDto(saved);
    }

    // GET BY ID
    @Override
    public ChallengeParticipantDto getParticipantById(Long id) {

        ChallengeParticipant entity = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Participant not found with id " + id));

        return ChallengeParticipantMapper.mapToDto(entity);
    }

    // GET ALL
    @Override
    public List<ChallengeParticipantDto> getAllParticipants() {

        return repository.findAll()
                .stream()
                .map(ChallengeParticipantMapper::mapToDto)
                .collect(Collectors.toList());
    }

    // UPDATE
    @Override
    public ChallengeParticipantDto updateParticipant(Long id, ChallengeParticipantDto dto) {

        ChallengeParticipant entity = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Participant not found with id " + id));

        if (dto.getProgress() != null) {
            entity.setProgress(dto.getProgress());
        }

        ChallengeParticipant updated = repository.save(entity);

        return ChallengeParticipantMapper.mapToDto(updated);
    }

    // DELETE
    @Override
    public void deleteParticipant(Long id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Participant not found with id " + id);
        }

        repository.deleteById(id);
    }
}