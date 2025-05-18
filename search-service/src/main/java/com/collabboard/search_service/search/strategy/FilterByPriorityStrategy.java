package com.collabboard.search_service.search.strategy;

import org.example.TaskDTO;
import com.collabboard.search_service.models.SearchRequest;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FilterByPriorityStrategy implements SearchStrategy {
    @Override
    public List<TaskDTO> search(List<TaskDTO> tasks, SearchRequest request) {
        if (request.getPriority() == null) return tasks;
        return tasks.stream()
                .filter(task -> {
                    try {
                        Field field = TaskDTO.class.getDeclaredField("priority");
                        field.setAccessible(true);
                        Object value = field.get(task);
                        return request.getPriority().equalsIgnoreCase(String.valueOf(value));
                    } catch (Exception e) {
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }
}
