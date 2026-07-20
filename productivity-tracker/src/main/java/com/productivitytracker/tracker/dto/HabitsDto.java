package com.productivitytracker.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HabitsDto {
    private Long id;
    private Long userId;
    private String name;
    private String frequency;
    private Integer targetCount;
}
