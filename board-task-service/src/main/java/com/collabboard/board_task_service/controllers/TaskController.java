package com.collabboard.board_task_service.controllers;


import com.collabboard.board_task_service.mapper.TaskMapper;
import org.example.TaskDTO;
import org.example.TaskType;

import com.collabboard.board_task_service.models.Task;
import com.collabboard.board_task_service.services.TaskService;
import org.example.Priority;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @GetMapping("/due-date/{date}")
    public ResponseEntity<List<Task>> getTasksByDueDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(taskService.getTasksByDueDate(localDate));
    }


    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Task>> getTasksByPriority(@PathVariable Priority priority) {
        return ResponseEntity.ok(taskService.getTasksByPriority(priority));
    }


//    @GetMapping("/assignee/{userId}")
//    public ResponseEntity<List<Task>> getTasksByAssignee(@PathVariable Long userId) {
//        return ResponseEntity.ok(taskService.getTasksByAssignee(userId));
//    }
    @GetMapping("/assignee/{userId}")
    public List<TaskDTO> getTasksByAssignee(@PathVariable Long userId) {
        System.out.println("Fetching tasks for assignee TASKCONTROLLER: " + userId);
        List<Task> tasks = taskService.getTasksByAssignee(userId);
        System.out.println("Fetched Tasks TASKCONTR: " + tasks);

        List<TaskDTO> taskDTOs = tasks.stream()
                .map(TaskMapper::toDTO)
                .toList();
        return taskDTOs;
    }

    @GetMapping("/filter")
    public List<TaskDTO> filterTasks(
            @RequestParam(required = false) LocalDate dueDate,
            @RequestParam(required = false) Long assigneeId,
            @RequestParam(required = false) Priority priority) {

        // Get filtered Task entities from the service
        List<Task> tasks = taskService.filterTasks(dueDate, assigneeId, priority);

        // Map to TaskDTOs in the controller using TaskMapper

        return tasks.stream()
                .map(TaskMapper::toDTO)
                .collect(Collectors.toList());
    }




}
