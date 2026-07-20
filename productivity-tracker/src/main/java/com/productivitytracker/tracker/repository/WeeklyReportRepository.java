package com.productivitytracker.tracker.repository;

import com.productivitytracker.tracker.entity.WeeklyReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeeklyReportRepository extends JpaRepository<WeeklyReport, Long> {
}
