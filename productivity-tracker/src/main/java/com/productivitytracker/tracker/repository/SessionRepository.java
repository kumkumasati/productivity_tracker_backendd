package com.productivitytracker.tracker.repository;

import com.productivitytracker.tracker.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}