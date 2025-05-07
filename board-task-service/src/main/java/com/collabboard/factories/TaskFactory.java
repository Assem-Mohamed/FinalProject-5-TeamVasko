package com.collabboard.factories;

import com.collabboard.enums.Priority;
import com.collabboard.enums.TaskType;
import com.collabboard.models.Task;

public class TaskFactory {

    public static Task createTask(TaskType type) {
        Task task = new Task();
        switch (type) {
            case BUG -> task.setPriority(Priority.HIGH);
            case FEATURE -> task.setPriority(Priority.MEDIUM);
            case IMPROVEMENT -> task.setPriority(Priority.LOW);
        }
        task.setTaskType(type); // assuming you added `TaskType type;` in Task model
        return task;
    }
}
