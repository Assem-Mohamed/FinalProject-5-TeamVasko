package com.collabboard.board_task_service.builders;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.example.Status;
import org.example.TaskType;
import com.collabboard.board_task_service.models.Task;
import org.example.Priority;

public class TaskBuilder {
    private String title;
    private String description;
    private LocalDate dueDate;
    private Priority priority;
    private Status status;
    private Set<Long> assigneeIds = new HashSet<>();
    private TaskType taskType;
    private Long createdBy;

    public TaskBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public TaskBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public TaskBuilder setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public TaskBuilder setPriority(Priority priority) {
        this.priority = priority;
        return this;
    }

    public TaskBuilder setStatus(Status status) {
        this.status = status;
        return this;
    }

    public TaskBuilder setAssigneeIds(Set<Long> assigneeIds) {
        this.assigneeIds = assigneeIds;
        return this;
    }

    public TaskBuilder setTaskType(TaskType taskType) {
        this.taskType = taskType;
        return this;
    }

    public TaskBuilder setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public Task build() {
        Task task = new Task();
        task.setTitle(this.title);
        task.setDescription(this.description);
        task.setDueDate(this.dueDate);
        task.setPriority(this.priority);
        task.setStatus(this.status);
        task.setAssigneeIds(this.assigneeIds);
        task.setTaskType(this.taskType);
        task.setCreatedBy(this.createdBy);
        return task;
    }
}
