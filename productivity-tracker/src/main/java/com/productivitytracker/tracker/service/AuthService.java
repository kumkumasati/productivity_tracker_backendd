package com.productivitytracker.tracker.service;

import com.productivitytracker.tracker.dto.auth.AuthResponse;
import com.productivitytracker.tracker.dto.auth.LoginRequest;
import com.productivitytracker.tracker.dto.auth.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
