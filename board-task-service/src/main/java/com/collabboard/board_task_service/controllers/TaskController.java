package com.collabboard.board_task_service.controllers;

import com.collabboard.board_task_service.enums.Priority;
import com.collabboard.board_task_service.enums.TaskType;
import com.collabboard.board_task_service.models.Task;
import com.collabboard.board_task_service.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> createTaskWithDesignPatterns(
            @RequestParam TaskType type,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam Long createdBy) {
        Task task = taskService.createTaskWithDesignPatterns(type, title, description, createdBy);
        return ResponseEntity.ok(task);
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
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAll() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PostMapping("/{id}/assign")
    public ResponseEntity<Task> assignUsers(@PathVariable Long id, @RequestBody Set<Long> userIds) {
        return ResponseEntity.ok(taskService.assignUsers(id, userIds));
    }

    @PutMapping("/{id}/priority")
    public ResponseEntity<Task> updatePriority(@PathVariable Long id, @RequestBody Priority priority) {
        return ResponseEntity.ok(taskService.updatePriority(id, priority));
    }

    @PutMapping("/{id}/dueDate")
    public ResponseEntity<Task> updateDueDate(@PathVariable Long id, @RequestBody LocalDate dueDate) {
        return ResponseEntity.ok(taskService.updateDueDate(id, dueDate));
    }

    @PatchMapping("/{id}/dueDate-if-missing")
    public ResponseEntity<Task> addDueDateIfMissing(@PathVariable Long id, @RequestBody LocalDate dueDate) {
        return ResponseEntity.ok(taskService.addDueDateIfMissing(id, dueDate));
    }
}
