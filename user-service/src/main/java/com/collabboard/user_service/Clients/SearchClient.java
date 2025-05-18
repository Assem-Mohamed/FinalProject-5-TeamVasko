package com.collabboard.user_service.Clients;

import org.example.SearchDTO;
import org.example.TaskDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "search-service") // or service discovery name
public interface SearchClient {
    @PostMapping("/api/search")
    List<TaskDTO> searchUserTasks(@RequestBody SearchDTO searchDTO);
}

