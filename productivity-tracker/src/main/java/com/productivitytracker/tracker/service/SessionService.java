package com.productivitytracker.tracker.service;

import com.productivitytracker.tracker.dto.SessionDto;

import java.util.List;

public interface SessionService {

    SessionDto createSession(SessionDto dto);

    SessionDto getById(Long id);

    List<SessionDto> getAll();

    SessionDto update(Long id, SessionDto dto);

    void delete(Long id);
}
