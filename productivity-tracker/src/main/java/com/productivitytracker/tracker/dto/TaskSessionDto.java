package com.productivitytracker.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskSessionDto {

    private Long sessionId;
    private String startTime;
    private String endTime;
    private Long duration;
    private Long userId;
    private Long taskId;
}
