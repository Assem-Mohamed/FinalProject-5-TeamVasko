package com.example.CommentLogService.Command;


import com.example.CommentLogService.CommentCommand;
import com.example.CommentLogService.Services.CommentService;

public class UnpinCommentCommand implements CommentCommand {
    private String commentId;

    public UnpinCommentCommand() {}

    public UnpinCommentCommand(String commentId) {
        this.commentId = commentId;
    }

    @Override
    public void execute(CommentService commentService) {
        commentService.unpinComment(commentId);
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }
}

