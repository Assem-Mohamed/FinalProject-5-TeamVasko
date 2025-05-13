package com.collabboard.search_reminder_service.Clients;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "board-task-service", url = "${board-task-service.url}")
public interface TaskClient {

    @GetMapping
    List<TaskDTO> getAllTasks();

    @GetMapping("/{id}")
    TaskDTO getTaskById(@PathVariable("id") Long id);
}
