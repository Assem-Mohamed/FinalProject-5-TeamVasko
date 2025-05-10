package com.collabboard.board_task_service.factories;


public class BugTaskFactory extends TaskFactory {
    @Override
    public BaseTask createTask() {
        return new BugTask();
    }
}

