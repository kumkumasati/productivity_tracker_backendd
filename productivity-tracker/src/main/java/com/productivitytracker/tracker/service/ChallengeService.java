package com.productivitytracker.tracker.service;

import com.productivitytracker.tracker.dto.ChallengeDto;

import java.util.List;

public interface ChallengeService {

    ChallengeDto createChallenge(ChallengeDto challengeDto);

    ChallengeDto getChallengeById(Long id);

    List<ChallengeDto> getAllChallenges();

    ChallengeDto updateChallenge(Long id, ChallengeDto updatedChallenge);

    void deleteChallenge(Long id);
}