package com.productivitytracker.tracker.mapper;

import com.productivitytracker.tracker.dto.WeeklyReportDto;
import com.productivitytracker.tracker.entity.User;
import com.productivitytracker.tracker.entity.WeeklyReport;

import java.time.LocalDate;

public class WeeklyReportMapper {

    public static WeeklyReportDto mapToDto(WeeklyReport report) {
        if (report == null) return null;

        return new WeeklyReportDto(
                report.getReportId(),
                report.getUser() != null ? report.getUser().getUserId() : null,
                report.getStrongestDay(),
                report.getWeakestDay(),
                report.getTotalTask(),
                report.getCompletedTask(),
                report.getTotalXp(),
                report.getCompletionRate(),
                report.getStreak(),
                report.getWeekStart() != null ? report.getWeekStart().toString() : null,
                report.getWeekEnd() != null ? report.getWeekEnd().toString() : null,
                report.getSummary(),
                report.getTips()
        );
    }

    public static WeeklyReport mapToEntity(WeeklyReportDto dto) {
        if (dto == null) return null;

        User user = null;
        if (dto.getUserId() != null) {
            user = new User();
            user.setUserId(dto.getUserId());
        }

        return new WeeklyReport(
                dto.getReportId(),
                dto.getStrongestDay(),
                dto.getWeakestDay(),
                dto.getTotalTask(),
                dto.getCompletedTask(),
                dto.getTotalXp(),
                dto.getCompletionRate(),
                dto.getStreak(),
                dto.getWeekStart() != null ? LocalDate.parse(dto.getWeekStart()) : null,
                dto.getWeekEnd() != null ? LocalDate.parse(dto.getWeekEnd()) : null,
                dto.getSummary(),
                dto.getTips(),
                user
        );
    }
}
