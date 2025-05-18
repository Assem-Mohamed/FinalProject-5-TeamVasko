package com.collabboard.board_task_service.repositories;

import com.collabboard.board_task_service.models.Task;
import org.example.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByDueDate(LocalDate dueDate);
    List<Task> findByAssigneeId(Long userId);
    List<Task> findByPriority(Priority priority);
    List<Task> findByAssigneeIdsContaining(Long userId);
}
