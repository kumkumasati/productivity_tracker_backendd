package com.productivitytracker.tracker.service.impl;

import com.productivitytracker.tracker.dto.XpLogDto;
import com.productivitytracker.tracker.entity.User;
import com.productivitytracker.tracker.entity.XpLog;
import com.productivitytracker.tracker.exception.ResourceNotFoundException;
import com.productivitytracker.tracker.mapper.XpLogMapper;
import com.productivitytracker.tracker.repository.UserRepository;
import com.productivitytracker.tracker.repository.XpLogRepository;
import com.productivitytracker.tracker.service.XpLogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class XpLogServiceImpl implements XpLogService {

    private XpLogRepository repository;
    private UserRepository userRepository;

    @Override
    public XpLogDto create(XpLogDto dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + dto.getUserId()));

        XpLog log = XpLogMapper.mapToEntity(dto);
        log.setUser(user); // attach managed entity

        XpLog saved = repository.save(log);

        return XpLogMapper.mapToDto(saved);
    }

    @Override
    public XpLogDto getById(Long id) {
        XpLog log = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("XpLog not found with id: " + id));

        return XpLogMapper.mapToDto(log);
    }

    @Override
    public List<XpLogDto> getAll() {
        return repository.findAll()
                .stream()
                .map(XpLogMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        XpLog log = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("XpLog not found with id: " + id));

        repository.delete(log);
    }
}