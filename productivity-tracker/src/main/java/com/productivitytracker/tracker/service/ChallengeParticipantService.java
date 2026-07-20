package com.productivitytracker.tracker.service;

import com.productivitytracker.tracker.dto.ChallengeParticipantDto;

import java.util.List;

public interface ChallengeParticipantService {

    ChallengeParticipantDto createParticipant(ChallengeParticipantDto dto);

    ChallengeParticipantDto getParticipantById(Long id);

    List<ChallengeParticipantDto> getAllParticipants();

    ChallengeParticipantDto updateParticipant(Long id, ChallengeParticipantDto dto);

    void deleteParticipant(Long id);
}