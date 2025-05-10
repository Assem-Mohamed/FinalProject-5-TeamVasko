package com.collabboard.board_task_service.factories;
import com.collabboard.board_task_service.enums.Priority;

public class ImprovementTask implements BaseTask {
    @Override
    public String getTitle() {
        return "Improvement";
    }

    @Override
    public String getType() {
        return "IMPROVEMENT";
    }

    @Override
    public Priority getPriority() {
        return Priority.LOW;
    }

    @Override
    public String getDescription() {
        return "Improve the existing feature in the system";
    }
}
