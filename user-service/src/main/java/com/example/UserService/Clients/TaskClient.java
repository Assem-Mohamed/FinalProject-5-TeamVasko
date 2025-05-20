package com.example.UserService.Clients;

import org.example.TaskDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "board-task-service1")
public interface TaskClient {
    @GetMapping("/api/tasks/assignee/{userId}")
    List<TaskDTO> getTasksByAssignee(@PathVariable("userId") Long userId);
}

