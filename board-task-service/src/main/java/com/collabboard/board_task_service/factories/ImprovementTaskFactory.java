package com.collabboard.board_task_service.factories;

public class ImprovementTaskFactory extends TaskFactory {
    @Override
    public BaseTask createTask() {
        return new ImprovementTask();
    }
}
