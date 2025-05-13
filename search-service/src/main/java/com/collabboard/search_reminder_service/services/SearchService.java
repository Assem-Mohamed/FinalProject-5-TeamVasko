package com.collabboard.search_reminder_service.services;

import com.collabboard.search_reminder_service.Clients.TaskClient;
import com.collabboard.search_reminder_service.Clients.TaskDTO;
import com.collabboard.search_reminder_service.Clients.UserClient;
import com.collabboard.search_reminder_service.Clients.UserDTO;
import com.collabboard.search_reminder_service.models.SearchContext;
import com.collabboard.search_reminder_service.models.SearchRequest;
import com.collabboard.search_reminder_service.models.TaskCache;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SearchService {
    private static final String SEARCH_CACHE_PREFIX = "search:";

    @Autowired private SearchContext searchContext;
    @Autowired private TaskClient taskClient;
    @Autowired private UserClient userClient;


    @Cacheable(value = "Tasks_SearchResult_Cache" , key = "#request.fullText" , condition = "#request.fullText != null")
    public List<TaskDTO> searchTasks(SearchRequest request) {
        List<TaskDTO> tasks = taskClient.getAllTasks(); // Fetch from Feign
        return searchContext.executeStrategies(tasks, request);
    }

}
