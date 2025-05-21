package com.example.UserService.Clients;

import jakarta.servlet.http.HttpSession;
import org.example.SearchDTO;
import org.example.TaskDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "search-service1" , url = "http://localhost:8089/api/search") // or service discovery name
public interface SearchClient {
    @PostMapping
    List<TaskDTO> searchTasks(@RequestBody SearchDTO searchDTO);
    @PostMapping("/filter")
    List<TaskDTO> filterTasks(@RequestBody SearchDTO searchDTO);
}

