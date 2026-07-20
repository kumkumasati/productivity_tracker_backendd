package com.productivitytracker.tracker.mapper;

import com.productivitytracker.tracker.dto.UserDto;
import com.productivitytracker.tracker.entity.User;

import java.time.LocalDate;

public class UserMapper {

    public static UserDto mapToUserDto(User user){
        if (user == null) return null;

        return new UserDto(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getCreatedAt() != null ? user.getCreatedAt().toString() : null
        );
    }

    public static User mapToUser(UserDto userDto){
        if (userDto == null) return null;

        return new User(
                userDto.getUserId(),                 // userId
                userDto.getUsername(),               // username
                userDto.getEmail(),                  // email
                userDto.getPassword(),               // password
                LocalDate.now(),                    // createdAt

                null, // tasks
                null, // sessions
                null, // xpLogs
                null, // notifications
                null, // reports
                null, // habits
                null, // habitLogs
                null, // taskLogs
                null, // auditLogs
                null, // dailyStats
                null, // timeEntries
                null, // userAchievements
                null, // challengeParticipants

                null, // profile
                null  // stats
        );
    }
}