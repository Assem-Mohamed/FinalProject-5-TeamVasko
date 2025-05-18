package com.collabboard.board_task_service.factories;
import org.example.Priority;


public interface BaseTask {
    String getTitle();
    String getType();
    String getDescription();
    Priority getPriority();
}
