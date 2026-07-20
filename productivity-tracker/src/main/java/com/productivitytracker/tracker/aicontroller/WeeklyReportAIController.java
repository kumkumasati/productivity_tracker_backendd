package com.productivitytracker.tracker.aicontroller;

import com.productivitytracker.tracker.aiservice.WeeklyReportAIService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@AllArgsConstructor
public class WeeklyReportAIController {

    private WeeklyReportAIService aiService;

    @GetMapping("/weekly-summary/{userId}")
    public String getSummary(@PathVariable Long userId) {
        return aiService.generateWeeklySummary(userId);
    }
}
