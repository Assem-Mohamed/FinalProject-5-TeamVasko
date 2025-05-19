package com.collabboard.search_service.Clients;

import org.example.TaskDTO;
import org.example.Priority;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name = "task-service", url = "${task-service.url}")
public interface TaskClient {

    @GetMapping("/api/tasks/filter")
    List<TaskDTO> filterTasks(
            @RequestParam(required = false) LocalDate dueDate,
            @RequestParam(required = false) Long assigneeId,
            @RequestParam(required = false) Priority priority
    );
}
