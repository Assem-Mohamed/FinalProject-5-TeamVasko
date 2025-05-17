package com.collabboard.search_service.services;

import org.example.TaskDTO;
import com.collabboard.search_service.models.SearchContext;
import com.collabboard.search_service.models.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private static final String SEARCH_CACHE_PREFIX = "search:";

    @Autowired private SearchContext searchContext;

    @Cacheable(value = "Tasks_SearchResult_Cache" , key = "#userId + ':' + #request.fullText", condition = "#request.fullText != null")
    public List<TaskDTO> searchTasks(SearchRequest request, Long userId){
        return searchContext.executeStrategies(request.getTasks(), request);   // Perform DB or API search
    }


}
