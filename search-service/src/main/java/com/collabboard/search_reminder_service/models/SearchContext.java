package com.collabboard.search_reminder_service.models;

import com.collabboard.search_reminder_service.Clients.TaskDTO;
import com.collabboard.search_reminder_service.search.strategy.SearchStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchContext {
    @Autowired
    private List<SearchStrategy> strategies;

    public List<TaskDTO> executeStrategies(List<TaskDTO> baseTasks, SearchRequest request) {
        List<TaskDTO> result = baseTasks;
        for (SearchStrategy strategy : strategies) {
            result = strategy.search(result, request);
        }
        return result;
    }
}
