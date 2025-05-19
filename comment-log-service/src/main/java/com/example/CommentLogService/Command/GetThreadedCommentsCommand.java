package com.example.CommentLogService.Command;

import com.example.CommentLogService.CommentCommand;
import com.example.CommentLogService.Services.CommentService;

public class GetThreadedCommentsCommand implements CommentCommand {
    private Long taskId;

    public GetThreadedCommentsCommand() {}

    public GetThreadedCommentsCommand(Long taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(CommentService commentService) {
        commentService.getThreadedComments(taskId);
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}

