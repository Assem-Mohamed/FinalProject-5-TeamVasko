package com.collabboard.board_task_service.factories;
import com.collabboard.board_task_service.enums.Priority;

public interface BaseTask {
    String getTitle();
    String getType();
    String getDescription();
    Priority getPriority();
}
