package com.collabboard.controllers;

import com.collabboard.builders.TaskBuilder;
import com.collabboard.enums.Priority;
import com.collabboard.factories.TaskFactory;
import com.collabboard.models.Task;
import com.collabboard.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Create task using factory + builder from raw Task input
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task input) {
        // Use the factory to set defaults based on task type
        Task base = TaskFactory.createTask(input.getTaskType());

        // Use builder to create the final Task with user input + defaults
        Task task = new TaskBuilder()
                .withTitle(input.getTitle())
                .withDescription(input.getDescription())
                .withDueDate(input.getDueDate())
                .withPriority(input.getPriority() != null ? input.getPriority() : base.getPriority())
                .withStatus(input.getStatus() != null ? input.getStatus() : base.getStatus())
                .withAssigneeIds(input.getAssigneeIds() != null ? input.getAssigneeIds() : Set.of())
                .withCreatedBy(input.getCreatedBy())
                .withType(input.getTaskType())
                .build();

        return ResponseEntity.ok(taskService.createTask(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task task) {
        return ResponseEntity.ok(taskService.updateTask(id, task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> get(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAll() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PostMapping("/{id}/assign")
    public ResponseEntity<Task> assignUsers(@PathVariable Long id, @RequestBody List<Long> userIds) {
        return ResponseEntity.ok(taskService.assignUsers(id, Set.copyOf(userIds)));
    }

    @PutMapping("/{id}/priority")
    public ResponseEntity<Task> updatePriority(@PathVariable Long id, @RequestBody String priority) {
        return ResponseEntity.ok(taskService.updatePriority(id, Priority.valueOf(priority)));
    }

    @PutMapping("/{id}/duedate")
    public ResponseEntity<Task> updateDueDate(@PathVariable Long id, @RequestBody String dueDate) {
        return ResponseEntity.ok(taskService.updateDueDate(id, LocalDate.parse(dueDate)));
    }
}