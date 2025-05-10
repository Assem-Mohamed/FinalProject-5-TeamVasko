package com.collabboard.board_task_service.controllers;

import com.collabboard.board_task_service.builders.TaskBuilder;
import com.collabboard.board_task_service.enums.Priority;
import com.collabboard.board_task_service.enums.TaskType;
import com.collabboard.board_task_service.factories.TaskFactory;
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
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task built = TaskFactory.createTask(task.getTaskType());

        Task finalTask = new TaskBuilder()
                .withTitle(task.getTitle())
                .withDescription(task.getDescription())
                .withDueDate(task.getDueDate())
                .withPriority(task.getPriority() != null ? task.getPriority() : built.getPriority())
                .withStatus(task.getStatus() != null ? task.getStatus() : built.getStatus())
                .withAssigneeIds(task.getAssigneeIds())
                .withCreatedBy(task.getCreatedBy())
                .withType(task.getTaskType())
                .build();

        Task saved = taskService.createTask(finalTask);
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
