//package com.productivitytracker.tracker.aiservice.aiimpl;
//
//import com.productivitytracker.tracker.aiservice.OpenAIService;
//import com.productivitytracker.tracker.repository.TaskLogsRepository;
//import com.productivitytracker.tracker.aiservice.WeeklyReportAIService;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//@AllArgsConstructor
//public class WeeklyReportAIServiceImpl implements WeeklyReportAIService {
//
//    private TaskLogsRepository taskLogsRepository;
//    private OpenAIService openAIService;
//
//    @Override
//    public String generateWeeklySummary(Long userId) {
//
//        LocalDateTime start = LocalDateTime.now().minusDays(7);
//
//        List<Object[]> logs =
//                taskLogsRepository.getWeeklyCompletedTasks(userId, start);
//
//        if (logs == null || logs.isEmpty()) {
//            return "No completed tasks found this week.";
//        }
//
//        StringBuilder data = new StringBuilder();
//
//        for (Object[] row : logs) {
//            String action = (String) row[0];
//            LocalDateTime date = (LocalDateTime) row[1];
//            String title = (String) row[2];
//
//            data.append("- ").append(title)
//                    .append(" (").append(action)
//                    .append(" on ").append(date.toLocalDate())
//                    .append(")\n");
//        }
//
//        System.out.println("Sending to AI:\n" + data);
//
//        return openAIService.generateWeeklySummary(data.toString());
//    }
//}
 package com.productivitytracker.tracker.aiservice.aiimpl;

import com.productivitytracker.tracker.aiservice.OpenAIService;
import com.productivitytracker.tracker.aiservice.WeeklyReportAIService;
import com.productivitytracker.tracker.entity.User;
import com.productivitytracker.tracker.entity.UserStats;
import com.productivitytracker.tracker.entity.WeeklyReport;
import com.productivitytracker.tracker.entity.XpLog;
import com.productivitytracker.tracker.repository.TaskLogsRepository;
import com.productivitytracker.tracker.repository.TaskRepository;
import com.productivitytracker.tracker.repository.UserRepository;
import com.productivitytracker.tracker.repository.UserStatsRepository;
import com.productivitytracker.tracker.repository.WeeklyReportRepository;
import com.productivitytracker.tracker.repository.XpLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class WeeklyReportAIServiceImpl implements WeeklyReportAIService {

    private TaskLogsRepository taskLogsRepository;
    private OpenAIService openAIService;
    private TaskRepository taskRepository;
    private UserStatsRepository userStatsRepository;
    private XpLogRepository xpLogRepository;
    private WeeklyReportRepository weeklyReportRepository;
    private UserRepository userRepository;

    @Override
    public String generateWeeklySummary(Long userId) {

        LocalDateTime start = LocalDateTime.now().minusDays(7);

        List<Object[]> logs =
                taskLogsRepository.getWeeklyCompletedTasks(userId, start);

        if (logs == null || logs.isEmpty()) {
            return "No completed tasks found this week.";
        }

        StringBuilder data = new StringBuilder();
        Map<DayOfWeek, Integer> completionsByDay = new EnumMap<>(DayOfWeek.class);
        int completedCount = 0;

        for (Object[] row : logs) {
            String action = (String) row[0];
            LocalDateTime date = (LocalDateTime) row[1];
            String title = (String) row[2];

            data.append("- ").append(title)
                    .append(" (").append(action)
                    .append(" on ").append(date.toLocalDate())
                    .append(")\n");

            if ("COMPLETED".equalsIgnoreCase(action)) {
                completedCount++;
                DayOfWeek day = date.getDayOfWeek();
                completionsByDay.merge(day, 1, Integer::sum);
            }
        }

        System.out.println("Sending to AI:\n" + data);

        String summaryText = openAIService.generateWeeklySummary(data.toString());

        saveWeeklyReportRow(userId, completedCount, completionsByDay, start, summaryText);

        return summaryText;
    }

    // Persists (or updates, if one already exists for this calendar week) a
    // WeeklyReport row so the frontend's stat tiles actually have data to
    // show, instead of "No saved report yet" forever.
    private void saveWeeklyReportRow(Long userId, int completedCount,
                                      Map<DayOfWeek, Integer> completionsByDay,
                                      LocalDateTime windowStart, String summaryText) {

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return;
        }

        LocalDate today = LocalDate.now();
        LocalDate weekStart = today.with(DayOfWeek.MONDAY);
        LocalDate weekEnd = weekStart.plusDays(6);

        int totalTask = taskRepository.findByUserUserId(userId).size();
        int completionRate = totalTask > 0 ? (completedCount * 100 / totalTask) : 0;

        int totalXp = xpLogRepository.findByUser_UserIdAndCreatedAtAfter(userId, windowStart)
                .stream()
                .mapToInt(XpLog::getAmount)
                .sum();

        int streak = userStatsRepository.findByUser_UserId(userId)
                .map(UserStats::getCurrentStreak)
                .orElse(0);

        String strongestDay = completionsByDay.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(e -> e.getKey().toString())
                .orElse("N/A");

        String weakestDay = completionsByDay.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(e -> e.getKey().toString())
                .orElse("N/A");

        WeeklyReport report = weeklyReportRepository.findByUser_UserIdAndWeekStart(userId, weekStart)
                .orElseGet(WeeklyReport::new);

        report.setUser(user);
        report.setWeekStart(weekStart);
        report.setWeekEnd(weekEnd);
        report.setTotalTask(totalTask);
        report.setCompletedTask(completedCount);
        report.setCompletionRate(completionRate);
        report.setTotalXp(totalXp);
        report.setStreak(streak);
        report.setStrongestDay(strongestDay);
        report.setWeakestDay(weakestDay);
        report.setSummary(summaryText.length() > 500 ? summaryText.substring(0, 500) : summaryText);

        weeklyReportRepository.save(report);
    }
}