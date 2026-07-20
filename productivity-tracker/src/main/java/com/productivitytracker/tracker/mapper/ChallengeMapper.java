package com.productivitytracker.tracker.mapper;

import com.productivitytracker.tracker.dto.ChallengeDto;
import com.productivitytracker.tracker.entity.Challenge;
import com.productivitytracker.tracker.entity.User;

import java.time.LocalDate;

public class ChallengeMapper {

    // ✅ DTO → Entity
    public static Challenge mapToChallenge(ChallengeDto dto) {

        Challenge challenge = new Challenge();

        challenge.setId(dto.getId());
        challenge.setTitle(dto.getTitle());
        challenge.setDescription(dto.getDescription());

        // Handle date conversion safely
        if (dto.getStartDate() != null) {
            challenge.setStartDate(LocalDate.parse(dto.getStartDate()));
        }

        if (dto.getEndDate() != null) {
            challenge.setEndDate(LocalDate.parse(dto.getEndDate()));
        }

        // createdBy handled in service (because it needs DB lookup)
        return challenge;
    }

    // ✅ Entity → DTO
    public static ChallengeDto mapToChallengeDto(Challenge challenge) {

        ChallengeDto dto = new ChallengeDto();

        dto.setId(challenge.getId());
        dto.setTitle(challenge.getTitle());
        dto.setDescription(challenge.getDescription());

        // Convert LocalDate → String
        if (challenge.getStartDate() != null) {
            dto.setStartDate(challenge.getStartDate().toString());
        }

        if (challenge.getEndDate() != null) {
            dto.setEndDate(challenge.getEndDate().toString());
        }

        // Convert User → userId
        if (challenge.getCreatedBy() != null) {
            dto.setCreatedBy(challenge.getCreatedBy().getUserId());
        }

        return dto;
    }
}