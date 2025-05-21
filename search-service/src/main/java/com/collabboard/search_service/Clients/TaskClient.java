package com.collabboard.search_service.Clients;

import org.example.TaskDTO;
import org.example.Priority;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name = "task-service", url = "http://localhost:8081/api/tasks")
public interface TaskClient {

    @GetMapping("/filter")
    List<TaskDTO> filterTasks(
            @RequestParam(required = false) LocalDate dueDate,
            @RequestParam(required = false) Long assigneeId,
            @RequestParam(required = false) Priority priority
    );
}
