package com.example.CommentLogService.Command;

import com.example.CommentLogService.CommentCommand;
import com.example.CommentLogService.Models.Comment;
import com.example.CommentLogService.Services.CommentService;

public class CreateCommentCommand implements CommentCommand {
    private Comment comment;

    public CreateCommentCommand() {}
    public CreateCommentCommand(Comment comment) {
        this.comment = comment;
    }

    @Override
    public void execute(CommentService commentService) {
        commentService.createComment(comment);
    }

    public Comment getComment() { return comment; }
    public void setComment(Comment comment) { this.comment = comment; }
}
