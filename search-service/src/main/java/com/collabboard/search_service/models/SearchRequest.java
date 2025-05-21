package com.collabboard.search_service.models;


import org.example.Priority;
import org.example.TaskDTO;
import java.time.LocalDate;
import java.util.List;

public class SearchRequest {
    private Long userId;
    private String fullText;
    private LocalDate dueDate;
    private Priority priority;
    private Long assignee;
    private String sortBy;
    private List<TaskDTO> tasks;

    // Getters and Setters
    public String getFullText() { return fullText; }
    public void setFullText(String fullText) { this.fullText = fullText; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public Long getAssignee() { return assignee; }
    public void setAssignee(Long assignee) { this.assignee = assignee; }
    public String getSortBy() { return sortBy; }
    public void setSortBy(String sortBy) { this.sortBy = sortBy; }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }
}
