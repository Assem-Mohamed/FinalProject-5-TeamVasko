package com.example.CommentLogService.Command;

import com.example.CommentLogService.CommentCommand;
import com.example.CommentLogService.Services.CommentService;

public class DeleteCommentCommand implements CommentCommand {
    private String id;

    public DeleteCommentCommand() {}
    public DeleteCommentCommand(String id) {
        this.id = id;
    }

    @Override
    public void execute(CommentService service) {
        service.deleteCommentById(id);
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
}

