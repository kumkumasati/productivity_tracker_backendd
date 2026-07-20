package com.productivitytracker.tracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@Entity
@Table(name="levels")
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long levelId;

    @Column(name = "level", nullable = false, unique = true)
    private Integer level;

    @Column(name="required_xp" , nullable = false)
    private Integer xpRequired;

    @OneToMany(mappedBy = "level")
    private List<UserStats> users;
}