package com.productivitytracker.tracker.service.impl;

import com.productivitytracker.tracker.dto.ChallengeDto;
import com.productivitytracker.tracker.entity.Challenge;
import com.productivitytracker.tracker.entity.User;
import com.productivitytracker.tracker.exception.ResourceNotFoundException;
import com.productivitytracker.tracker.mapper.ChallengeMapper;
import com.productivitytracker.tracker.repository.ChallengeRepository;
import com.productivitytracker.tracker.repository.UserRepository;
import com.productivitytracker.tracker.service.ChallengeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {

    private ChallengeRepository challengeRepository;
    private UserRepository userRepository;

    // ✅ CREATE
    @Override
    public ChallengeDto createChallenge(ChallengeDto challengeDto) {

        Challenge challenge = ChallengeMapper.mapToChallenge(challengeDto);

        if (challengeDto.getCreatedBy() != null) {
            User user = userRepository.findById(challengeDto.getCreatedBy())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("User does not exist with id " + challengeDto.getCreatedBy()));
            challenge.setCreatedBy(user);
        }

        Challenge saved = challengeRepository.save(challenge);

        return ChallengeMapper.mapToChallengeDto(saved);
    }

    // ✅ GET BY ID
    @Override
    public ChallengeDto getChallengeById(Long challengeId) {

        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Challenge does not exist with id " + challengeId));

        return ChallengeMapper.mapToChallengeDto(challenge);
    }

    // ✅ GET ALL
    @Override
    public List<ChallengeDto> getAllChallenges() {

        return challengeRepository.findAll()
                .stream()
                .map(ChallengeMapper::mapToChallengeDto)
                .collect(Collectors.toList());
    }

    // ✅ UPDATE (FIXED CLEAN VERSION)
    @Override
    public ChallengeDto updateChallenge(Long challengeId, ChallengeDto updatedDto) {

        Challenge existing = challengeRepository.findById(challengeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Challenge does not exist with id " + challengeId));

        // Convert DTO → Entity (handles date conversion)
        Challenge updated = ChallengeMapper.mapToChallenge(updatedDto);

        // Preserve ID
        updated.setId(existing.getId());

        // Handle relationship
        if (updatedDto.getCreatedBy() != null) {
            User user = userRepository.findById(updatedDto.getCreatedBy())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("User does not exist with id " + updatedDto.getCreatedBy()));
            updated.setCreatedBy(user);
        } else {
            updated.setCreatedBy(existing.getCreatedBy());
        }

        Challenge saved = challengeRepository.save(updated);

        return ChallengeMapper.mapToChallengeDto(saved);
    }

    // ✅ DELETE
    @Override
    public void deleteChallenge(Long challengeId) {

        if (!challengeRepository.existsById(challengeId)) {
            throw new ResourceNotFoundException("Challenge does not exist with id " + challengeId);
        }

        challengeRepository.deleteById(challengeId);
    }
}