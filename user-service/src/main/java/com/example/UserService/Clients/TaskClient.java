package com.example.UserService.Clients;

import org.example.TaskDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "task-service") // Adjust URL as needed
public interface TaskClient {

    @GetMapping("/user/{userId}")
    List<TaskDTO> getTasksByUserId(@RequestParam("userId") Long userId);
}
