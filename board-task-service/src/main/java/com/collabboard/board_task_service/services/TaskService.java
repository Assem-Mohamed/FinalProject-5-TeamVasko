package com.collabboard.board_task_service.services;

import com.collabboard.board_task_service.enums.Priority;
import com.collabboard.board_task_service.enums.Status;
import com.collabboard.board_task_service.enums.TaskType;
import com.collabboard.board_task_service.models.Task;
import com.collabboard.builder.TaskBuilder;
import com.collabboard.factory.TaskFactory;
import com.collabboard.board_task_service.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;


@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // Read all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Read single task by ID
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // Update an existing task
    public Task updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id).map(existingTask -> {
            if (updatedTask.getTitle() != null)
                existingTask.setTitle(updatedTask.getTitle());

            if (updatedTask.getDescription() != null)
                existingTask.setDescription(updatedTask.getDescription());

            if (updatedTask.getDueDate() != null)
                existingTask.setDueDate(updatedTask.getDueDate());

            if (updatedTask.getPriority() != null)
                existingTask.setPriority(updatedTask.getPriority());

            if (updatedTask.getStatus() != null)
                existingTask.setStatus(updatedTask.getStatus());

            if (updatedTask.getAssigneeIds() != null && !updatedTask.getAssigneeIds().isEmpty())
                existingTask.setAssigneeIds(updatedTask.getAssigneeIds());

            if (updatedTask.getTaskType() != null)
                existingTask.setTaskType(updatedTask.getTaskType());

            if (updatedTask.getCreatedBy() != null)
                existingTask.setCreatedBy(updatedTask.getCreatedBy());
            return taskRepository.save(existingTask);
        }).orElseThrow(() -> new NoSuchElementException("Task not found with id " + id));
    }

    // Delete task
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    // Assign users to a task
    public Task assignUsers(Long taskId, Set<Long> userIds) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task not found"));
        task.getAssigneeIds().addAll(userIds);
        return taskRepository.save(task);
    }

    // Update due date
    public Task updateDueDate(Long taskId, LocalDate newDueDate) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task not found"));
        task.setDueDate(newDueDate);
        return taskRepository.save(task);
    }

    // Update priority
    public Task updatePriority(Long taskId, Priority newPriority) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task not found"));
        task.setPriority(newPriority);
        return taskRepository.save(task);
    }

    // Send notification to assigned users (via external service)
    public void notifyAssignees(Task task, String message) {
        for (Long userId : task.getAssigneeIds()) {
            Map<String, Object> notification = new HashMap<>();
            notification.put("recipientId", userId);
            notification.put("message", message);
            restTemplate.postForObject("http://notification-service/notifications", notification, Void.class);
        }
    }

    // Example: Update status & notify users
    public Task updateStatusAndNotify(Long taskId, Status status) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task not found"));
        task.setStatus(status);
        Task saved = taskRepository.save(task);
        notifyAssignees(saved, "Task \"" + task.getTitle() + "\" updated to " + status);
        return saved;
    }

    // Schedule reminder (placeholder, assumes external scheduler integration)
    public void scheduleReminder(Long taskId, String reminderMessage, LocalDate reminderDate) {
        // In a real system, you'd integrate with a job scheduler (Quartz, Spring Scheduler, etc.)
        // For now, simulate via logging or placeholder
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task not found"));
        // Example: Notify now if due today (just for mock)
        if (LocalDate.now().equals(reminderDate)) {
            notifyAssignees(task, reminderMessage);
        }
    }

    // Create task using factory + builder
    public Task createTaskWithFactoryAndBuilder(TaskType type, String title, String description, Long createdBy) {
        Task base = TaskFactory.createTask(type);
        Task task = new TaskBuilder()
                .setTaskType(type)
                .setTitle(title)
                .setDescription(description)
                .setCreatedBy(createdBy)
                .setPriority(base.getPriority())
                .setStatus(base.getStatus())
                .build();
        return taskRepository.save(task);
    }

}
