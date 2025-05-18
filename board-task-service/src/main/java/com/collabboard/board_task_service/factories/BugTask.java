package com.collabboard.board_task_service.factories;
import org.example.Priority;


public class BugTask implements BaseTask {
    @Override
    public String getTitle() {
        return "Bug Fix";
    }

    @Override
    public String getType() {
        return "BUG";
    }

    @Override
    public Priority getPriority() {
        return Priority.HIGH;
    }

    @Override
    public String getDescription() {
        return "Fix the bug in the system";
    }

}
