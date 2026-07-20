package com.productivitytracker.tracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@Entity
@Table(name="habits")
public class Habits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long habitId;

    @Column(name="habit_name")
    private String habitName;

    @Column(name="frequency")
    private String frequency;

    @Column(name = "target_count")
    private Integer targetCount;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable=false)
    private User user;
}

