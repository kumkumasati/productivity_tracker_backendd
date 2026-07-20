package com.productivitytracker.tracker.service.impl;

import com.productivitytracker.tracker.dto.auth.AuthResponse;
import com.productivitytracker.tracker.dto.auth.LoginRequest;
import com.productivitytracker.tracker.dto.auth.RegisterRequest;
import com.productivitytracker.tracker.entity.User;
import com.productivitytracker.tracker.exception.BadCredentialsAppException;
import com.productivitytracker.tracker.exception.EmailAlreadyExistsException;
import com.productivitytracker.tracker.repository.UserRepository;
import com.productivitytracker.tracker.security.JwtService;
import com.productivitytracker.tracker.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse register(RegisterRequest request) {
        userRepository.findByEmail(request.getEmail()).ifPresent(u -> {
            throw new EmailAlreadyExistsException("An account with this email already exists");
        });

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User saved = userRepository.save(user);

        String token = jwtService.generateToken(saved.getUserId(), saved.getEmail());
        return new AuthResponse(token, saved.getUserId(), saved.getUsername(), saved.getEmail());
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadCredentialsAppException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsAppException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getUserId(), user.getEmail());
        return new AuthResponse(token, user.getUserId(), user.getUsername(), user.getEmail());
    }
}
