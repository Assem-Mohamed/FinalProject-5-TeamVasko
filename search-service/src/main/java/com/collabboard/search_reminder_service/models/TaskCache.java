package com.collabboard.search_reminder_service.models;

import com.collabboard.search_reminder_service.Clients.TaskDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class TaskCache {
    private final List<TaskDTO> cachedTasks = new CopyOnWriteArrayList<>();

    public List<TaskDTO> getAllTasks() {
        return new ArrayList<>(cachedTasks);
    }

    public void updateCache(List<TaskDTO> tasks) {
        cachedTasks.clear();
        cachedTasks.addAll(tasks);
    }

    public void addTask(TaskDTO task) {
        cachedTasks.add(task);
    }
}