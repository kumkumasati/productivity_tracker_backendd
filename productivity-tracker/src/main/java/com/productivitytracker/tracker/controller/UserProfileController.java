package com.productivitytracker.tracker.controller;
import com.productivitytracker.tracker.dto.UserProfileDto;
import com.productivitytracker.tracker.service.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/api/users/{userId}/profile")

public class UserProfileController {
    private UserProfileService userProfileService;
    //build add user restapi
    @PostMapping
    public ResponseEntity<UserProfileDto> createProfile(
            @PathVariable Long userId,
            @RequestBody UserProfileDto dto) {

        UserProfileDto savedProfile = userProfileService.createProfile(userId, dto);
        return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
    }

    //Build get user REST api
    @GetMapping
    public ResponseEntity<UserProfileDto> getProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(userProfileService.getProfileByUserId(userId));
    }
    //Build update user REST api
    @PutMapping
    public ResponseEntity<UserProfileDto> updateProfile(
            @PathVariable Long userId,
            @RequestBody UserProfileDto dto) {

        return ResponseEntity.ok(userProfileService.updateProfile(userId, dto));
    }
    //build delete user REST api
    @DeleteMapping
    public ResponseEntity<String> deleteProfile(@PathVariable Long userId) {
        userProfileService.deleteProfile(userId);
        return ResponseEntity.ok("Profile deleted successfully!");
    }
}

