package com.collabboard.search_reminder_service.search.strategy;

import com.collabboard.search_reminder_service.Clients.TaskDTO;
import com.collabboard.search_reminder_service.models.SearchRequest;


import java.util.List;

public interface SearchStrategy {
    List<TaskDTO> search(List<TaskDTO> tasks, SearchRequest request);
}
