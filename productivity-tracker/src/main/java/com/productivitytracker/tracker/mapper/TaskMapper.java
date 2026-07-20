package com.productivitytracker.tracker.mapper;
import com.productivitytracker.tracker.dto.TaskDto;
import com.productivitytracker.tracker.entity.Task;

public class TaskMapper {

    public static TaskDto mapToTaskDto(Task task){
        return new TaskDto(
                task.getTaskId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate()
        );
    }

    public static Task mapToTask(TaskDto dto){
        Task task = new Task();
        task.setTaskId(dto.getTaskId());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setPriority(dto.getPriority());
        task.setDueDate(dto.getDueDate());
        return task;
    }
}