package com.example.UserService.Clients;

import org.example.TaskDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "board-task-service1", url = "http://localhost:8081/api/tasks")
public interface TaskClient {
    @GetMapping("/assignee/{userId}")
    List<TaskDTO> getTasksByAssignee(@PathVariable("userId") Long userId);
}
