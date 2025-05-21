package com.collabboard.search_service.controllers;

import com.collabboard.search_service.mapper.SearchMapper;
import com.collabboard.search_service.models.SearchRequest;
import com.collabboard.search_service.services.SearchService;
import org.example.SearchDTO;
import org.example.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping
    public List<TaskDTO> searchTasks(
            @RequestBody SearchDTO dto,
            HttpSession session
    ) {
        Long userId = (Long) session.getAttribute("userId");

        // Override userId from session if needed
        dto.setUserId(userId);

        // (Optional) Map to internal SearchRequest model if you still want to work with that inside the service
        SearchRequest request = SearchMapper.toRequest(dto);

        // Pass request to service
        return searchService.searchTasks(request, userId);
    }
    @PostMapping("/filter")
    public List<TaskDTO> filterTasks(
            @RequestBody SearchDTO dto,
            HttpSession session
    ) {
        Long userId = (Long) session.getAttribute("userId");
        dto.setUserId(userId);

        SearchRequest request = SearchMapper.toRequest(dto);
        return searchService.filterTasks(request.getDueDate(),request.getUserId(),request.getPriority()); // You need to implement this in the service
    }




}
