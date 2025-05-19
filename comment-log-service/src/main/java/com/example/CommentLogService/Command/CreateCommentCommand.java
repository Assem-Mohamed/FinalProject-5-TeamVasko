package com.example.CommentLogService.Command;

import com.example.CommentLogService.CommentCommand;
import com.example.CommentLogService.Models.Comment;
import com.example.CommentLogService.Services.CommentService;

public class CreateCommentCommand implements CommentCommand {

    private final Comment comment;

    public CreateCommentCommand(Comment comment) {
        this.comment = comment;
    }

    @Override
    public void execute(CommentService commentService) {
        commentService.createComment(comment);
    }
}
