package com.productivitytracker.tracker.repository;

import com.productivitytracker.tracker.entity.TaskCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskCategoryRepository extends JpaRepository<TaskCategory, Long> {
}
