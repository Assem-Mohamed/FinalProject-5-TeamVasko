package com.collabboard.search_reminder_service.controllers;

import com.collabboard.search_reminder_service.Clients.TaskDTO;
import com.collabboard.search_reminder_service.models.SearchRequest;
import com.collabboard.search_reminder_service.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @PostMapping
    public ResponseEntity<List<TaskDTO>> searchTasks(@RequestBody SearchRequest request) {
        return ResponseEntity.ok(searchService.searchTasks(request));
    }
}

