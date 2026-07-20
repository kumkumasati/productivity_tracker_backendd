package com.productivitytracker.tracker.mapper;

import com.productivitytracker.tracker.dto.TaskCategoryMapDto;
import com.productivitytracker.tracker.entity.Task;
import com.productivitytracker.tracker.entity.TaskCategory;
import com.productivitytracker.tracker.entity.TaskCategoryMap;

public class TaskCategoryMapMapper {

    // Entity → DTO
    public static TaskCategoryMapDto mapToDto(TaskCategoryMap entity) {
        if (entity == null) return null;

        return new TaskCategoryMapDto(
                entity.getTaskCategoryMapId(),
                entity.getTask() != null ? entity.getTask().getTaskId() : null,
                entity.getCategory() != null ? entity.getCategory().getTaskCategoryId() : null
        );
    }

    // DTO → Entity
    public static TaskCategoryMap mapToEntity(TaskCategoryMapDto dto) {
        if (dto == null) return null;

        Task task = null;
        if (dto.getTaskId() != null) {
            task = new Task();
            task.setTaskId(dto.getTaskId());
        }

        TaskCategory category = null;
        if (dto.getCategoryId() != null) {
            category = new TaskCategory();
            category.setTaskCategoryId(dto.getCategoryId());
        }

        return new TaskCategoryMap(
                dto.getTaskCategoryMapId(),
                task,
                category
        );
    }
}