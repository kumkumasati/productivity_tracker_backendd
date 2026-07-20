package com.productivitytracker.tracker.service;
import com.productivitytracker.tracker.dto.UserProfileDto;
public interface UserProfileService {

    UserProfileDto createProfile(Long userId, UserProfileDto dto);
    UserProfileDto getProfileByUserId(Long userId);
    UserProfileDto updateProfile(Long userId, UserProfileDto dto);
    void deleteProfile(Long userId);
}