package com.collabboard.search_service.search.strategy;

import org.example.TaskDTO;
import com.collabboard.search_service.models.SearchRequest;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FullTextSearchStrategy implements SearchStrategy {
    @Override
    public List<TaskDTO> search(List<TaskDTO> tasks, SearchRequest request) {
        if (request.getFullText() == null || request.getFullText().isEmpty()) return tasks;
        String query = request.getFullText().toLowerCase();

        return tasks.stream()
                .filter(task -> {
                    try {
                        Field titleField = TaskDTO.class.getDeclaredField("title");
                        titleField.setAccessible(true);
                        Object value = titleField.get(task);
                        return value != null && value.toString().toLowerCase().contains(query);
                    } catch (IllegalAccessException | NoSuchFieldException e) {
                        e.printStackTrace();
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }
}
