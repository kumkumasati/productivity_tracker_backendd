package com.productivitytracker.tracker.aiservice.aiimpl;

import com.productivitytracker.tracker.aiservice.OpenAIService;
import com.productivitytracker.tracker.repository.TaskLogsRepository;
import com.productivitytracker.tracker.aiservice.WeeklyReportAIService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class WeeklyReportAIServiceImpl implements WeeklyReportAIService {

    private TaskLogsRepository taskLogsRepository;
    private OpenAIService openAIService;

    @Override
    public String generateWeeklySummary(Long userId) {

        LocalDateTime start = LocalDateTime.now().minusDays(7);

        List<Object[]> logs =
                taskLogsRepository.getWeeklyCompletedTasks(userId, start);

        if (logs == null || logs.isEmpty()) {
            return "No completed tasks found this week.";
        }

        StringBuilder data = new StringBuilder();

        for (Object[] row : logs) {
            String action = (String) row[0];
            LocalDateTime date = (LocalDateTime) row[1];
            String title = (String) row[2];

            data.append("- ").append(title)
                    .append(" (").append(action)
                    .append(" on ").append(date.toLocalDate())
                    .append(")\n");
        }

        System.out.println("Sending to AI:\n" + data);

        return openAIService.generateWeeklySummary(data.toString());
    }
}