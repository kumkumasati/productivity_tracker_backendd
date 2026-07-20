package com.productivitytracker.tracker.repository;

import com.productivitytracker.tracker.entity.Habits;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitsRepository extends JpaRepository<Habits, Long> {
}