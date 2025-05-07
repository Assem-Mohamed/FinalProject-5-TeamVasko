package com.collabboard.dto;

import com.collabboard.enums.Status;
import com.collabboard.enums.TaskType;

import java.time.LocalDate;
import java.util.Set;

public class TaskRequestDTO {
    private String title;
    private String description;
    private LocalDate dueDate;
    private String priority;
    private Status status;
    private Set<Long> assigneeIds;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<Long> getAssigneeIds() {
        return assigneeIds;
    }

    public void setAssigneeIds(Set<Long> assigneeIds) {
        this.assigneeIds = assigneeIds;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    private Long createdBy;
    private TaskType type;

}
