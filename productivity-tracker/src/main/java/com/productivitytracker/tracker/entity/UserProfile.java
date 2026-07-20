package com.productivitytracker.tracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@Entity
@Table(name="user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="gender")
    private String gender;

    @Column(name="phone_number")
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

}