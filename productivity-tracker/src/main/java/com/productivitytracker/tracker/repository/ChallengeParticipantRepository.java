package com.productivitytracker.tracker.repository;

import com.productivitytracker.tracker.entity.ChallengeParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeParticipantRepository extends JpaRepository<ChallengeParticipant, Long>
{

    boolean existsByChallengeIdAndUserUserId(Long challengeId, Long userId);
}