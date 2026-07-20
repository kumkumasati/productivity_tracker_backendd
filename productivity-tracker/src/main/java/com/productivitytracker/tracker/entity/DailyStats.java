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
        name = "daily_stats",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "date"})
        }
)
public class DailyStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" ,nullable = false)
    private User user;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "tasks_completed")
    private Integer tasksCompleted = 0;

    @Column(name = "xp_earned")
    private Integer xpEarned = 0;
}