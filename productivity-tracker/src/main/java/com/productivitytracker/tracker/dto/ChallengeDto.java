package com.productivitytracker.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeDto {

    private Long id;

    private String title;

    private String description;

    // Keep as String for easy API input/output (mapper handles conversion)
    private String startDate;

    private String endDate;

    // Only store userId, not full User object
    private Long createdBy;
}