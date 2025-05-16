package com.collabboard.board_task_service.factories;
import com.collabboard.board_task_service.enums.Priority;

public class FeatureTask implements BaseTask {
    @Override
    public String getTitle() {
        return "New Feature";
    }

    @Override
    public String getType() {
        return "FEATURE";
    }

    @Override
    public Priority getPriority() {
        return Priority.MEDIUM;
    }

    @Override
    public String getDescription() {
        return "Implement a new feature in the system";
    }

}
