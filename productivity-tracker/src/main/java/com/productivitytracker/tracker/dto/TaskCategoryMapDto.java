package com.productivitytracker.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class TaskCategoryMapDto {
    private Long taskCategoryMapId;
    private Long taskId;
    private Long categoryId;
}