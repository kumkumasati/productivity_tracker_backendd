package com.productivitytracker.tracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@Entity
@Table(
        name = "weekly_report",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "week_start"})
        }
)
public class WeeklyReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @Column(name = "strongest_day", nullable = false)
    private String strongestDay;

    @Column(name = "weakest_day", nullable = false)
    private String weakestDay;

    @Column(name = "total_task", nullable = false)
    private Integer totalTask;

    @Column(name = "completed_task", nullable = false)
    private Integer completedTask;

    @Column(name = "total_xp")
    private Integer totalXp;

    @Column(name = "completion_rate")
    private Integer completionRate;

    @Column(name = "streak")
    private Integer streak;

    @Column(name = "week_start", nullable = false)
    private LocalDate weekStart;

    @Column(name = "week_end", nullable = false)
    private LocalDate weekEnd;

    @Column(name = "summary", length = 500)
    private String summary;

    @Column(name = "tips", length = 500)
    private String tips;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // removed unique=true
    private User user;
}