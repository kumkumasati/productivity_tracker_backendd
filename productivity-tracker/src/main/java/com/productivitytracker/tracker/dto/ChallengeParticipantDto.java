package com.productivitytracker.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeParticipantDto {
    private Long id;
    private Long challengeId;
    private Long userId;
    private Integer progress;
    private String joinedAt;
}
