package com.collabboard.board_task_service.factories;

public class FeatureTaskFactory extends TaskFactory {
    @Override
    public BaseTask createTask() {
        return new FeatureTask();
    }
}