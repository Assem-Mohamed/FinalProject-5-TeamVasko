package com.collabboard.board_task_service.services;


import com.collabboard.board_task_service.builders.TaskBuilder;
import org.example.Priority;

import org.example.Status;
import org.example.TaskType;
import com.collabboard.board_task_service.factories.BaseTask;
import com.collabboard.board_task_service.factories.BugTaskFactory;
import com.collabboard.board_task_service.factories.ImprovementTaskFactory;
import com.collabboard.board_task_service.factories.FeatureTaskFactory;
import com.collabboard.board_task_service.models.Task;
import com.collabboard.board_task_service.rabbitmq.NotificationMessage;
import com.collabboard.board_task_service.rabbitmq.RabbitMQProducer;
import com.collabboard.board_task_service.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

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

            Task saved = taskRepository.save(existingTask);

            notifyAssignees(saved, "Notification: task \"" + saved.getTitle() + "\" has been updated, please check it");
            return saved;
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
        Task saved = taskRepository.save(task);

        notifyAssignees(saved,
                "Notification: task \"" + saved.getTitle() + "\" due date has been updated to " + newDueDate);
        return saved;
    }

    // Update priority
    public Task updatePriority(Long taskId, Priority newPriority) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task not found"));

        task.setPriority(newPriority);
        Task saved = taskRepository.save(task);

        notifyAssignees(saved,
                "Notification: task \"" + saved.getTitle() + "\" priority has been updated to " + newPriority);
        return saved;
    }

    public Task addDueDateIfMissing(Long taskId, LocalDate dueDate) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task not found"));

        if (task.getDueDate() == null) {
            task.setDueDate(dueDate);
            Task saved = taskRepository.save(task);

            notifyAssignees(saved,
                    "Notification: task \"" + saved.getTitle() + "\" has been given a new due date: " + dueDate);
            return saved;
        }

        return task; // no change
    }

    // Send notification to assigned users (via external service)
    public void notifyAssignees(Task task, String message) {
        task.getAssigneeIds()
                .forEach(userId -> rabbitMQProducer.sendNotification(new NotificationMessage(userId, message)));
    }

    @Scheduled(cron = "0 0 8 * * *") // every day at 8 AM
    public void sendDeadlineReminders() {
        LocalDate today = LocalDate.now();
        List<Task> tasks = taskRepository.findAll();

        for (Task task : tasks) {
            if (task.getDueDate() == null)
                continue;
            if (!EnumSet.of(Status.TODO, Status.IN_PROGRESS, Status.REVIEW).contains(task.getStatus()))
                continue;

            if (task.getDueDate().equals(today.plusDays(3))) {
                notifyAssignees(task, "Reminder: task \"" + task.getTitle() + "\" deadline is in less than 3 days");
            }
        }
    }

    public Task createTaskWithDesignPatterns(TaskType type, String title, String description, Long createdBy) {
        BaseTask baseTask;

        switch (type) {
            case BUG -> baseTask = new BugTaskFactory().createTask();
            case FEATURE -> baseTask = new FeatureTaskFactory().createTask();
            case IMPROVEMENT -> baseTask = new ImprovementTaskFactory().createTask();
            default -> throw new IllegalArgumentException("Unknown task type: " + type);
        }

        Task task = new TaskBuilder()
                .setTitle(title != null ? title : baseTask.getTitle())
                .setDescription(description != null ? description : baseTask.getDescription())
                .setPriority(baseTask.getPriority())
                .setStatus(Status.TODO)
                .setTaskType(type)
                .setCreatedBy(createdBy)
                .setAssigneeIds(new HashSet<>())
                .build();

        return taskRepository.save(task);

    }

    public List<Task> getTasksByAssignee(Long userId) {
        System.out.println("Finding tasks assigned to user ID TASKSERVICE: " + userId);


        return taskRepository.findByAssigneeIdsContaining(userId);
    }

    public List<Task> getTasksByDueDate(LocalDate date) {
        return taskRepository.findByDueDate(date);
    }


    public List<Task> getTasksByPriority(Priority priority) {
        return taskRepository.findByPriority(priority);
    }
    public List<Task> filterTasks(LocalDate dueDate, Long assigneeId, Priority priority) {
        List<Task> tasks = taskRepository.findAll();
        System.out.println("due date:" + dueDate);
        System.out.println("priority:" + priority);
        System.out.println("assignee id:" + assigneeId);

        if (dueDate != null) {
            tasks = tasks.stream()
                    .filter(task -> dueDate.equals(task.getDueDate()))
                    .collect(Collectors.toList());
        }

        if (assigneeId != null) {
            tasks = tasks.stream()
                    .filter(task -> task.getAssigneeIds().contains(assigneeId))
                    .collect(Collectors.toList());
        }

        if (priority != null) {
            tasks = tasks.stream()
                    .filter(task -> priority.equals(task.getPriority()))
                    .collect(Collectors.toList());
        }

        return tasks;
    }


}
