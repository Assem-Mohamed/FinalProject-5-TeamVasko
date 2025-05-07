package com.collabboard.controllers;

import com.collabboard.builders.TaskBuilder;
import com.collabboard.dto.TaskRequestDTO;
import com.collabboard.enums.Priority;
import com.collabboard.enums.TaskType;
import com.collabboard.factories.TaskFactory;
import com.collabboard.models.Task;
import com.collabboard.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskRequestDTO request) {
        Task task = TaskFactory.createTask(request.getType());
        Priority priority = Priority.valueOf(request.getPriority());

        task = new TaskBuilder()
                .withTitle(request.getTitle())
                .withDescription(request.getDescription())
                .withDueDate(request.getDueDate())
                .withPriority(priority)
                .withStatus(request.getStatus())
                .withAssigneeIds(request.getAssigneeIds())
                .withCreatedBy(request.getCreatedBy())
                .withType(request.getType())
                .build();

        Task saved = taskService.createTask(task);
        return ResponseEntity.ok(saved);
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
        return ResponseEntity.ok(taskService.assignUsers(id, userIds));
    }

    @PutMapping("/{id}/priority")
    public ResponseEntity<Task> updatePriority(@PathVariable Long id, @RequestBody String priority) {
        return ResponseEntity.ok(taskService.updatePriority(id, priority));
    }

    @PutMapping("/{id}/duedate")
    public ResponseEntity<Task> updateDueDate(@PathVariable Long id, @RequestBody String dueDate) {
        return ResponseEntity.ok(taskService.updateDueDate(id, dueDate));
    }
}
