package com.productivitytracker.tracker.repository;

import com.productivitytracker.tracker.entity.XpLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface XpLogRepository extends JpaRepository<XpLog, Long> {
}