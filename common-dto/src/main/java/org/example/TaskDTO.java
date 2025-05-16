package org.example;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

public class TaskDTO implements Serializable {
    private Long id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private Priority priority;
    private Set<Long> assignees;

    public TaskDTO(String title, String description, LocalDate dueDate, Priority priority, Set<Long> assignees) {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Set<Long> getAssignees() {
        return assignees;
    }

    public void setAssignees(Set<Long> assignees) {
        this.assignees = assignees;
    }
}