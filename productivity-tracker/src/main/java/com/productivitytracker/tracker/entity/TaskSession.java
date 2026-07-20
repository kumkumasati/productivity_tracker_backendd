package com.productivitytracker.tracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@Entity
@Table(name="TaskSessions")
public class TaskSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskSessionId;

    @Column(name="start_time")
    private LocalDateTime startTime;

    @Column(name="end_time")
    private LocalDateTime endTime;

    @Column(name="duration")
    private Long duration;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;
}
