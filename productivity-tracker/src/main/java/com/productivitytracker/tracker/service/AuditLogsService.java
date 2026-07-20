package com.productivitytracker.tracker.service;

import com.productivitytracker.tracker.dto.AuditLogsDto;

import java.util.List;

public interface AuditLogsService {

    AuditLogsDto createAuditLog(AuditLogsDto auditLogsDto);

    AuditLogsDto getById(Long id);

    List<AuditLogsDto> getAllLogs();

    void delete(Long id);
}