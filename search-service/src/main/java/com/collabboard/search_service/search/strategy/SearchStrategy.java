package com.collabboard.search_service.search.strategy;

import org.example.TaskDTO;
import com.collabboard.search_service.models.SearchRequest;


import java.util.List;

public interface SearchStrategy {
    List<TaskDTO> search(List<TaskDTO> tasks, SearchRequest request);
}
