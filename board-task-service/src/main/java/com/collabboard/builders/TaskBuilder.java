package com.collabboard.builders;

import com.collabboard.enums.Priority;
import com.collabboard.enums.Status;
import com.collabboard.enums.TaskType;
import com.collabboard.models.Task;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class TaskBuilder {
    private final Task task;

    public TaskBuilder() {
        this.task = new Task();
    }

    public TaskBuilder withTitle(String title) {
        task.setTitle(title);
        return this;
    }

    public TaskBuilder withDescription(String description) {
        task.setDescription(description);
        return this;
    }

    public TaskBuilder withDueDate(LocalDate dueDate) {
        task.setDueDate(dueDate);
        return this;
    }

    public TaskBuilder withPriority(Priority priority) {
        task.setPriority(priority);
        return this;
    }

    public TaskBuilder withStatus(Status status) {
        task.setStatus(status);
        return this;
    }

    public TaskBuilder withAssigneeIds(Set<Long> assigneeIds) {
        task.setAssigneeIds(new HashSet<>(assigneeIds));
        return this;
    }

    public TaskBuilder withCreatedBy(Long createdBy) {
        task.setCreatedBy(createdBy);
        return this;
    }

    public TaskBuilder withType(TaskType type) {
        task.setTaskType(type);
        return this;
    }

    public Task build() {
        return task;
    }
}
