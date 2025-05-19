package com.example.CommentLogService.Command;

import com.example.CommentLogService.CommentCommand;
import com.example.CommentLogService.Models.Comment;
import com.example.CommentLogService.Services.CommentService;

public class UpdateCommentCommand implements CommentCommand {
    private String id;
    private Comment updatedComment;

    public UpdateCommentCommand() {}
    public UpdateCommentCommand(String id, Comment updatedComment) {
        this.id = id;
        this.updatedComment = updatedComment;
    }

    @Override
    public void execute(CommentService service) {
        service.updateComment(id, updatedComment);
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Comment getUpdatedComment() { return updatedComment; }
    public void setUpdatedComment(Comment updatedComment) { this.updatedComment = updatedComment; }
}

