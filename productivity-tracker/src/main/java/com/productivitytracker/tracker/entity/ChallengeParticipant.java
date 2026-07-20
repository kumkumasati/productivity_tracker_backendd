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
        name = "challenge_participants",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"challenge_id", "user_id"})
        }
)
public class ChallengeParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "progress")
    private Integer progress = 0;

    @Column(name = "joined_at")
    private LocalDateTime joinedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void prePersist() {
        this.joinedAt = LocalDateTime.now();
        if (this.progress == null) {
            this.progress = 0;
        }
    }
}