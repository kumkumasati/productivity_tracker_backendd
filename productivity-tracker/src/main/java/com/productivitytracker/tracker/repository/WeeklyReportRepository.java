package com.productivitytracker.tracker.repository;

import com.productivitytracker.tracker.entity.WeeklyReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface WeeklyReportRepository extends JpaRepository<WeeklyReport, Long> {

    Optional<WeeklyReport> findByUser_UserIdAndWeekStart(Long userId, LocalDate weekStart);
}
