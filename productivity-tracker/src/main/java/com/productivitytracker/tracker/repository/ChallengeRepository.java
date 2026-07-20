package com.productivitytracker.tracker.repository;

import com.productivitytracker.tracker.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
}