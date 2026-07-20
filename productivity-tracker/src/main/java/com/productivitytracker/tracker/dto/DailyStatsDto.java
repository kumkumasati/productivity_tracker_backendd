package com.productivitytracker.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyStatsDto {
    private Long id;
    private Long userId;
    private String date;
    private Integer tasksCompleted;
    private Integer xpEarned;
}
