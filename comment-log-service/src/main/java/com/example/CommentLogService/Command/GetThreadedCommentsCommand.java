package com.example.CommentLogService.Command;

import com.example.CommentLogService.CommentCommand;
import com.example.CommentLogService.Models.Comment;
import com.example.CommentLogService.Services.CommentService;

import java.util.List;

public class GetThreadedCommentsCommand implements CommentCommand<List<Comment>> {

    private final CommentService commentService;
    private final Long taskId;

    public GetThreadedCommentsCommand(CommentService commentService, Long taskId) {
        this.commentService = commentService;
        this.taskId = taskId;
    }

    @Override
    public List<Comment> execute() {
        return commentService.getThreadedComments(taskId);
    }
}
