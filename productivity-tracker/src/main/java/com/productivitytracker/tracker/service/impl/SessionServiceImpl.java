package com.productivitytracker.tracker.service.impl;

import com.productivitytracker.tracker.dto.SessionDto;
import com.productivitytracker.tracker.entity.Session;
import com.productivitytracker.tracker.entity.User;
import com.productivitytracker.tracker.exception.ResourceNotFoundException;
import com.productivitytracker.tracker.mapper.SessionMapper;
import com.productivitytracker.tracker.repository.SessionRepository;
import com.productivitytracker.tracker.repository.UserRepository;
import com.productivitytracker.tracker.service.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SessionServiceImpl implements SessionService {

    private SessionRepository sessionRepository;
    private UserRepository userRepository;

    // ✅ CREATE
    @Override
    public SessionDto createSession(SessionDto dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + dto.getUserId()));

        Session session = SessionMapper.mapToEntity(dto);
        session.setUser(user); // override with real entity

        Session saved = sessionRepository.save(session);

        return SessionMapper.mapToDto(saved);
    }

    // ✅ GET BY ID
    @Override
    public SessionDto getById(Long id) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Session not found with id: " + id));

        return SessionMapper.mapToDto(session);
    }

    // ✅ GET ALL
    @Override
    public List<SessionDto> getAll() {
        return sessionRepository.findAll()
                .stream()
                .map(SessionMapper::mapToDto)
                .collect(Collectors.toList());
    }

    // ✅ UPDATE
    @Override
    public SessionDto update(Long id, SessionDto dto) {

        Session session = sessionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Session not found with id: " + id));

        session.setToken(dto.getToken());

        if (dto.getExpiresAt() != null) {
            session.setExpiresAt(LocalDateTime.parse(dto.getExpiresAt()));
        }

        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("User not found with id: " + dto.getUserId()));

            session.setUser(user);
        }

        Session updated = sessionRepository.save(session);

        return SessionMapper.mapToDto(updated);
    }

    // ✅ DELETE
    @Override
    public void delete(Long id) {

        Session session = sessionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Session not found with id: " + id));

        sessionRepository.delete(session);
    }
}