package com.productivitytracker.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HabitLogsDto {

    private Long id;
    private Long habitId;
    private Long userId;
    private String date;
    private Boolean completed;
    private Integer count;
}