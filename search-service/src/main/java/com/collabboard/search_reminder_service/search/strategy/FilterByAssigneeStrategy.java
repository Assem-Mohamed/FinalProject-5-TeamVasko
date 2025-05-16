package com.collabboard.search_reminder_service.search.strategy;

import org.example.TaskDTO;import com.collabboard.search_reminder_service.models.SearchRequest;


import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FilterByAssigneeStrategy implements SearchStrategy {
    @Override
    public List<TaskDTO> search(List<TaskDTO> tasks, SearchRequest request) {
        if (request.getAssignee() == null) return tasks;
        return tasks.stream()
                .filter(task -> {
                    try {
                        Field field = TaskDTO.class.getDeclaredField("assignee");
                        field.setAccessible(true);
                        Object value = field.get(task);
                        return request.getAssignee().equalsIgnoreCase(String.valueOf(value));
                    } catch (Exception e) {
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }
}
