package com.example.CommentLogService.Command;


import com.example.CommentLogService.CommentCommand;
import com.example.CommentLogService.Models.Comment;
import com.example.CommentLogService.Services.CommentService;

public class UnpinCommentCommand implements CommentCommand<Comment> {

    private final CommentService commentService;
    private final String commentId;

    public UnpinCommentCommand(CommentService commentService, String commentId) {
        this.commentService = commentService;
        this.commentId = commentId;
    }

    @Override
    public Comment execute() {
        return commentService.unpinComment(commentId);
    }
}
