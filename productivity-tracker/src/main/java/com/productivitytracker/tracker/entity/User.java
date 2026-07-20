package com.productivitytracker.tracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name="user_name" ,nullable = false,length = 50)
    private String username;

    @Column(name="email_id", nullable=false, unique=true ,length = 150)
    private String email;

    @Column(name="email_password", nullable=false)
    private String password;

    @Column(name="createdAt", nullable=false)
    private LocalDate createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDate.now();
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Task> tasks;

    @OneToMany(mappedBy = "user")
    private List<Session> sessions;

    @OneToMany(mappedBy = "user")
    private List<XpLog> xpLogs;

    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;

    @OneToMany(mappedBy = "user")
    private List<WeeklyReport> reports;

    @OneToMany(mappedBy = "user")
    private List<Habits> habits;

    @OneToMany(mappedBy = "user")
    private List<HabitLogs> habitLogs;

    @OneToMany(mappedBy = "user")
    private List<TaskLogs> taskLogs;

    @OneToMany(mappedBy = "user")
    private List<AuditLogs> auditLogs;

    @OneToMany(mappedBy = "user")
    private List<DailyStats> dailyStats;

    @OneToMany(mappedBy = "user")
    private List<TimeEntries> timeEntries;

    @OneToMany(mappedBy = "user")
    private List<UserAchievement> userAchievements;

    @OneToMany(mappedBy = "user")
    private List<ChallengeParticipant> challengeParticipants;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserProfile profile;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserStats stats;

}