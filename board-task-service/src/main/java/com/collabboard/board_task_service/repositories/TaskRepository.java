package com.collabboard.board_task_service.repositories;

import com.collabboard.board_task_service.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByDueDate(LocalDate dueDate);
    List<Task> findByAssigneeIdsContaining(Long userId);
}
