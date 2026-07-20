package com.productivitytracker.tracker.repository;

import com.productivitytracker.tracker.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    // Custom method to fetch profile using userId
    Optional<UserProfile> findByUserUserId(Long userId);
}