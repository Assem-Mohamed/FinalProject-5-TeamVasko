package com.collabboard.board_task_service.mapper;

import org.example.TaskDTO;
import com.collabboard.board_task_service.models.Task;


public class TaskMapper {
    public static TaskDTO toDTO(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus(),
                task.getTaskType(),
                task.getAssigneeIds(),
                task.getCreatedAt(),
                task.getUpdatedAt(),
                task.getCreatedBy()
        );
    }
}

