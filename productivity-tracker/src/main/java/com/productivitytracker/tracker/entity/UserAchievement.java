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
@Table(
        name = "user_achievements",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "achievement_id"})
        }
)
public class UserAchievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" ,nullable=false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "achievement_id" ,nullable =false)
    private Achievement achievement;

    @Column(name = "earned_at")
    private LocalDateTime earnedAt;

    @PrePersist
    public void prePersist() {
        this.earnedAt = LocalDateTime.now();
    }
}