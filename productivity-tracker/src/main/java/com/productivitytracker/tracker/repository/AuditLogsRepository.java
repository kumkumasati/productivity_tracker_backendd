package com.productivitytracker.tracker.repository;

import com.productivitytracker.tracker.entity.AuditLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogsRepository extends JpaRepository<AuditLogs, Long> {
}
