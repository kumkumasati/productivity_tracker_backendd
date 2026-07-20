package com.productivitytracker.tracker.dto.auth;

public record AuthResponse(String token, Long userId, String username, String email) {
}
