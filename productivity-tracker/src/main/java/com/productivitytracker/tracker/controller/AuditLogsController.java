package com.productivitytracker.tracker.controller;

import com.productivitytracker.tracker.dto.AuditLogsDto;
import com.productivitytracker.tracker.service.AuditLogsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/audit-logs")
public class AuditLogsController {

    private AuditLogsService auditLogsService;

    // CREATE Audit Log
    @PostMapping
    public ResponseEntity<AuditLogsDto> createAuditLog(@RequestBody AuditLogsDto auditLogsDto) {
        AuditLogsDto savedLog = auditLogsService.createAuditLog(auditLogsDto);
        return new ResponseEntity<>(savedLog, HttpStatus.CREATED);
    }

    // GET Audit Log by ID
    @GetMapping("{id}")
    public ResponseEntity<AuditLogsDto> getAuditLogById(@PathVariable("id") Long id) {
        AuditLogsDto auditLogsDto = auditLogsService.getById(id);
        return ResponseEntity.ok(auditLogsDto);
    }

    // GET All Audit Logs
    @GetMapping
    public ResponseEntity<List<AuditLogsDto>> getAllAuditLogs() {
        List<AuditLogsDto> logs = auditLogsService.getAllLogs();
        return ResponseEntity.ok(logs);
    }

    // DELETE Audit Log
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAuditLog(@PathVariable("id") Long id) {
        auditLogsService.delete(id);
        return ResponseEntity.ok("Audit log deleted successfully!");
    }
}