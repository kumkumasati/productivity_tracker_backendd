package com.productivitytracker.tracker.mapper;

import com.productivitytracker.tracker.dto.TaskCategoryDto;
import com.productivitytracker.tracker.entity.TaskCategory;

public class TaskCategoryMapper {

    // ✅ Entity → DTO
    public static TaskCategoryDto mapToDto(TaskCategory category) {
        if (category == null) return null;

        return new TaskCategoryDto(
                category.getTaskCategoryId(),
                category.getName()
        );
    }

    // ✅ DTO → Entity
    public static TaskCategory mapToEntity(TaskCategoryDto dto) {
        if (dto == null) return null;

        return new TaskCategory(
                dto.getId(),
                dto.getName()
        );
    }
}