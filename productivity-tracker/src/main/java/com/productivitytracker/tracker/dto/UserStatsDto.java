package com.productivitytracker.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserStatsDto {

    private Long id;
    private Long userId;
    private Integer totalXp;
    private Long levelId;
    private Integer currentStreak;
    private Integer longestStreak;
    private String lastActivityDate;
}