package com.productivitytracker.tracker.service.impl;

import com.productivitytracker.tracker.dto.AuditLogsDto;
import com.productivitytracker.tracker.entity.AuditLogs;
import com.productivitytracker.tracker.entity.User;
import com.productivitytracker.tracker.repository.AuditLogsRepository;
import com.productivitytracker.tracker.repository.UserRepository;
import com.productivitytracker.tracker.service.AuditLogsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AuditLogsServiceImpl implements AuditLogsService {

    private AuditLogsRepository auditLogsRepository;
    private UserRepository userRepository;

    @Override
    public AuditLogsDto createAuditLog(AuditLogsDto auditLogsDto) {

        User user = userRepository.findById(auditLogsDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        AuditLogs auditLog = new AuditLogs();
        auditLog.setAction(auditLogsDto.getAction());
        auditLog.setMetadata(auditLogsDto.getMetadata());
        auditLog.setCreatedAt(LocalDateTime.now());
        auditLog.setUser(user);

        AuditLogs savedLog = auditLogsRepository.save(auditLog);

        return mapToDto(savedLog);
    }

    @Override
    public AuditLogsDto getById(Long id) {
        AuditLogs log = auditLogsRepository.findById(id)
                .orElseThrow(() -> new  com.productivitytracker.tracker.exception.ResourceNotFoundException(
                        "Audit log not found with id: " + id));
        return mapToDto(log);
    }

    @Override
    public List<AuditLogsDto> getAllLogs() {
        return auditLogsRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        auditLogsRepository.deleteById(id);
    }

    // 🔁 Entity → DTO mapping
    private AuditLogsDto mapToDto(AuditLogs log) {
        return new AuditLogsDto(
                log.getId(),
                log.getUser() != null ? log.getUser().getUserId() : null,
                log.getAction(),
                log.getMetadata(),
                log.getCreatedAt() != null ? log.getCreatedAt().toString() : null
        );
    }
}