package com.example.CommentLogService.Command;


import com.example.CommentLogService.CommentCommand;
import com.example.CommentLogService.Models.Comment;
import com.example.CommentLogService.Services.CommentService;

public class PinCommentCommand implements CommentCommand<Comment> {

    private final CommentService commentService;
    private final String commentId;

    public PinCommentCommand(CommentService commentService, String commentId) {
        this.commentService = commentService;
        this.commentId = commentId;
    }

    @Override
    public Comment execute() {
        return commentService.pinComment(commentId);
    }
}
