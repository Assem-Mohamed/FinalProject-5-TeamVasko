package com.collabboard.search_reminder_service.controllers;

import com.collabboard.search_reminder_service.models.SearchRequest;
import com.collabboard.search_reminder_service.services.SearchService;
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
    public ResponseEntity<List<TaskDTO>> searchTasks(
            @RequestBody SearchRequest request,
            HttpSession session
    ) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return ResponseEntity.status(401).body(null); // Unauthorized
        }

        List<TaskDTO> results = searchService.searchTasks(request, userId);
        return ResponseEntity.ok(results);
    }
}
