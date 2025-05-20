package com.example.CommentLogService.Command;

import com.example.CommentLogService.CommentCommand;
import com.example.CommentLogService.Services.CommentService;

public class DeleteCommentCommand implements CommentCommand<Void> {

    private final CommentService commentService;
    private final String commentId;

    public DeleteCommentCommand(CommentService commentService, String commentId) {
        this.commentService = commentService;
        this.commentId = commentId;
    }

    @Override
    public Void execute() {
        commentService.deleteCommentById(commentId);
        return null;
    }
}
