package com.productivitytracker.tracker.repository;

import com.productivitytracker.tracker.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {

    List<Achievement> findByTitle(String title);

    List<Achievement> findByXpRewardGreaterThan(Integer xp);
}