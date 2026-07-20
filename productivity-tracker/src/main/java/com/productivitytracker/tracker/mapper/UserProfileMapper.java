package com.productivitytracker.tracker.mapper;

import com.productivitytracker.tracker.dto.UserProfileDto;
import com.productivitytracker.tracker.entity.UserProfile;

public class UserProfileMapper {

    // Entity → DTO
    public static UserProfileDto mapToUserProfileDto(UserProfile userProfile){
        return new UserProfileDto(
                userProfile.getUser().getUserId(),
                userProfile.getProfileId(),
                userProfile.getFirstName(),
                userProfile.getLastName(),
                userProfile.getGender(),
                userProfile.getPhoneNumber()
        );
    }

    // DTO → Entity
    public static UserProfile mapToUserProfile(UserProfileDto dto){
        return new UserProfile(
                dto.getProfileId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getGender(),
                dto.getPhoneNumber(),
                null
        );
    }
}
