package com.collabboard.search_reminder_service.search.strategy;

import com.collabboard.search_reminder_service.Clients.TaskDTO;
import com.collabboard.search_reminder_service.models.SearchRequest;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SortByFieldStrategy implements SearchStrategy {
    @Override
    public List<TaskDTO> search(List<TaskDTO> tasks, SearchRequest request) {
        if (request.getSortBy() == null || request.getSortBy().isEmpty()) return tasks;

        String sortField = request.getSortBy();

        try {
            Field field = TaskDTO.class.getDeclaredField(sortField);
            field.setAccessible(true);

            Comparator<TaskDTO> comparator = Comparator.comparing(task -> {
                try {
                    Object value = field.get(task);
                    return (value instanceof Comparable) ? (Comparable<Object>) value : null;
                } catch (IllegalAccessException e) {
                    return null;
                }
            });

            return tasks.stream().sorted(comparator).collect(Collectors.toList());

        } catch (NoSuchFieldException e) {
            return tasks; // fallback to unsorted
        }
    }
}
