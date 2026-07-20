//package com.productivitytracker.tracker.dto;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class WeeklyReportDto {
//    private Long id;
//    private Long userId;
//    private String weekStart;
//    private String summary;
//    private String aiTips;
//    private String strongestDay;
//    private String weakestDay;
//    private Integer totalTasks;
//    private Integer completedTasks;
//    private Double completionRate;
//}
package com.productivitytracker.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeeklyReportDto {

    private Long ReportId;
    private Long userId;

    private String strongestDay;
    private String weakestDay;

    private Integer totalTask;
    private Integer completedTask;
    private Integer totalXp;
    private Integer completionRate;
    private Integer streak;

    private String weekStart;
    private String weekEnd;

    private String summary;
    private String tips;
}