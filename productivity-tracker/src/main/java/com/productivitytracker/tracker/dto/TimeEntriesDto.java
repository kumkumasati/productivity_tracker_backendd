package com.productivitytracker.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeEntriesDto {
    private Long id;
    private Long userId;
    private Long taskId;
    private Integer durationMinutes;
    private String createdAt;
}
