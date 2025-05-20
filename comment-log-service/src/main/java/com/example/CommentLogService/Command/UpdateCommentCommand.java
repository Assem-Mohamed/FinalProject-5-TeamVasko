package com.example.CommentLogService.Command;

import com.example.CommentLogService.CommentCommand;
import com.example.CommentLogService.Models.Comment;
import com.example.CommentLogService.Services.CommentService;

public class UpdateCommentCommand implements CommentCommand<Comment> {

    private final CommentService commentService;
    private final String commentId;
    private final Comment updatedComment;

    public UpdateCommentCommand(CommentService commentService, String commentId, Comment updatedComment) {
        this.commentService = commentService;
        this.commentId = commentId;
        this.updatedComment = updatedComment;
    }

    @Override
    public Comment execute() {
        return commentService.updateComment(commentId, updatedComment);
    }
}
