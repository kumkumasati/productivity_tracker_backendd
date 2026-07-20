package com.productivitytracker.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TaskLogsDto {
    private Long id;
    private Long taskId;
    private Long userId;
    private String action;
    private String createdAt;
}
