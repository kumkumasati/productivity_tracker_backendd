package com.productivitytracker.tracker.mapper;

import com.productivitytracker.tracker.dto.ChallengeParticipantDto;
import com.productivitytracker.tracker.entity.ChallengeParticipant;

public class ChallengeParticipantMapper {

    // ✅ DTO → Entity (Constructor-based)
    public static ChallengeParticipant mapToEntity(ChallengeParticipantDto dto) {

        return new ChallengeParticipant(
                dto.getId(),          // id
                dto.getProgress(),    // progress
                null,                 // joinedAt (handled by @PrePersist)
                null,                 // challenge (set in service)
                null                  // user (set in service)
        );
    }

    // ✅ Entity → DTO (Constructor-based)
    public static ChallengeParticipantDto mapToDto(ChallengeParticipant entity) {

        return new ChallengeParticipantDto(
                entity.getId(), // ✅ consistent now
                entity.getChallenge() != null ? entity.getChallenge().getId() : null,
                entity.getUser() != null ? entity.getUser().getUserId() : null,
                entity.getProgress(),
                entity.getJoinedAt() != null ? entity.getJoinedAt().toString() : null
        );
    }
}