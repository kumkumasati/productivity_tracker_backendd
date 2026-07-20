package com.productivitytracker.tracker.service.impl;

import com.productivitytracker.tracker.dto.UserProfileDto;
import com.productivitytracker.tracker.entity.User;
import com.productivitytracker.tracker.entity.UserProfile;
import com.productivitytracker.tracker.exception.ResourceNotFoundException;
import com.productivitytracker.tracker.mapper.UserProfileMapper;
import com.productivitytracker.tracker.repository.UserProfileRepository;
import com.productivitytracker.tracker.repository.UserRepository;
import com.productivitytracker.tracker.service.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private UserProfileRepository userProfileRepository;
    private UserRepository userRepository;

    // ✅ Create Profile
    @Override
    public UserProfileDto createProfile(Long userId, UserProfileDto dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id " + userId));

        // 🔥 Prevent duplicate profile
        if (userProfileRepository.findByUserUserId(userId).isPresent()) {
            throw new RuntimeException("Profile already exists for user id " + userId);
        }

        UserProfile profile = UserProfileMapper.mapToUserProfile(dto);
        profile.setUser(user);

        UserProfile savedProfile = userProfileRepository.save(profile);

        return UserProfileMapper.mapToUserProfileDto(savedProfile);
    }

    // ✅ Get Profile
    @Override
    public UserProfileDto getProfileByUserId(Long userId) {

        UserProfile profile = userProfileRepository.findByUserUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Profile not found for user id " + userId));

        return UserProfileMapper.mapToUserProfileDto(profile);
    }

    // ✅ Update Profile
    @Override
    public UserProfileDto updateProfile(Long userId, UserProfileDto dto) {

        UserProfile profile = userProfileRepository.findByUserUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Profile not found for user id " + userId));

        profile.setFirstName(dto.getFirstName());
        profile.setLastName(dto.getLastName());
        profile.setGender(dto.getGender());
        profile.setPhoneNumber(dto.getPhoneNumber());

        User updated = profile.getUser(); // optional check if needed

        UserProfile updatedProfile = userProfileRepository.save(profile);

        return UserProfileMapper.mapToUserProfileDto(updatedProfile);
    }

    // ✅ Delete Profile
    @Override
    public void deleteProfile(Long userId) {

        UserProfile profile = userProfileRepository.findByUserUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Profile not found for user id " + userId));

        userProfileRepository.delete(profile);
    }
}