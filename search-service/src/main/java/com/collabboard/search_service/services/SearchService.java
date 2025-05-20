package com.collabboard.search_service.services;

import com.collabboard.search_service.Clients.TaskClient;
import org.example.Priority;
import org.example.TaskDTO;
import com.collabboard.search_service.models.SearchContext;
import com.collabboard.search_service.models.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@EnableFeignClients(basePackages = "com.collabboard.search_service.clients")

public class SearchService {
    private static final String SEARCH_CACHE_PREFIX = "search:";

    @Autowired private SearchContext searchContext;

    @Autowired
    private TaskClient taskClient;

    @Cacheable(value = "Search Results" , key = "#userId + ':' + #request.fullText", condition = "#request.fullText != null")
    public List<TaskDTO> searchTasks(SearchRequest request, Long userId){
        return searchContext.executeStrategies(request.getTasks(), request);   // Perform DB or API search
    }
    public List<TaskDTO> filterTasks(LocalDate dueDate, Long assigneeId, Priority priority) {
        return taskClient.filterTasks(dueDate, assigneeId, priority);
    }

}
