package com.productivitytracker.tracker.repository;

import com.productivitytracker.tracker.entity.TaskCategoryMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskCategoryMapRepository extends JpaRepository<TaskCategoryMap, Long> {
}