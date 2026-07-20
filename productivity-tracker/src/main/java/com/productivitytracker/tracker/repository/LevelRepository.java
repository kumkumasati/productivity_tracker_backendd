package com.productivitytracker.tracker.repository;

import com.productivitytracker.tracker.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LevelRepository extends JpaRepository<Level, Long> {

    Optional<Level> findByLevel(Integer level); // useful for validation
}