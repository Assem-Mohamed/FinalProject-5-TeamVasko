package org.example;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class SearchDTO implements Serializable {
    private Long userId;
    private String fullText;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // or "yyyy-MM-dd'T'HH:mm:ss"
    private LocalDate dueDate;
    private Priority priority;
    private Long assignee;
    private String sortBy;
    private List<TaskDTO> tasks;

    public SearchDTO() {
    }

    // Getters and Setters


    public SearchDTO(Long userId, String fullText, LocalDate dueDate, Priority priority, Long assignee, String sortBy, List<TaskDTO> tasks) {
        this.userId = userId;
        this.fullText = fullText;
        this.dueDate = dueDate;
        this.priority = priority;
        this.assignee = assignee;
        this.sortBy = sortBy;
        this.tasks = tasks;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Long getAssignee() {
        return assignee;
    }

    public void setAssignee(Long assignee) {
        this.assignee = assignee;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }
}
