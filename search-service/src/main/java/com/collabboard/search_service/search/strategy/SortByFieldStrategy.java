package com.collabboard.search_service.search.strategy;

import org.example.TaskDTO;
import com.collabboard.search_service.models.SearchRequest;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class SortByFieldStrategy implements SearchStrategy {
    @Override
    public List<TaskDTO> search(List<TaskDTO> tasks, SearchRequest request) {
        String sortField = request.getSortBy();

        if (sortField == null || sortField.isEmpty()) {
            return tasks;
        }

        try {
            Field field = TaskDTO.class.getDeclaredField(sortField.trim());
            field.setAccessible(true);

            Comparator<TaskDTO> comparator = Comparator.comparing(task -> {
                try {
                    Object value = field.get(task);
                    return (value instanceof Comparable) ? (Comparable<Object>) value : null;
                } catch (IllegalAccessException e) {
                    return null;
                }
            }, Comparator.nullsLast(Comparator.naturalOrder()));

            return tasks.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());

        } catch (NoSuchFieldException e) {
            // If the sort field doesn't exist, just return unsorted
            return tasks;
        }
    }
}
