package com.collabboard.search_service.mapper;

import com.collabboard.search_service.models.SearchRequest;
import org.example.SearchDTO;

public class SearchMapper {

    public static SearchDTO toDTO(SearchRequest request) {
        SearchDTO dto = new SearchDTO();
        dto.setUserId(request.getUserId()); // This might be overridden later with session value
        dto.setFullText(request.getFullText());
        dto.setDueDate(request.getDueDate());
        dto.setPriority(request.getPriority());
        dto.setAssignee(request.getAssignee());
        dto.setSortBy(request.getSortBy());
        dto.setTasks(request.getTasks());
        return dto;
    }
    public static SearchRequest toRequest(SearchDTO dto) {
        SearchRequest request = new SearchRequest();
        request.setUserId(dto.getUserId());
        request.setFullText(dto.getFullText());
        request.setDueDate(dto.getDueDate());
        request.setPriority(dto.getPriority());
        request.setAssignee(dto.getAssignee());
        request.setSortBy(dto.getSortBy());
        request.setTasks(dto.getTasks());
        return request;
    }
}
