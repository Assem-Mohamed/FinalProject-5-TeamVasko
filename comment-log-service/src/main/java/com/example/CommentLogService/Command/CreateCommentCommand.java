package com.example.CommentLogService.Command;

import com.example.CommentLogService.CommentCommand;
import com.example.CommentLogService.Models.Comment;
import com.example.CommentLogService.Services.CommentService;

public class CreateCommentCommand implements CommentCommand<Comment> {

    private final CommentService commentService;
    private final Comment comment;

    public CreateCommentCommand(CommentService commentService, Comment comment) {
        this.commentService = commentService;
        this.comment = comment;
    }

    @Override
    public Comment execute() {
        return commentService.createComment(comment);
    }
}
