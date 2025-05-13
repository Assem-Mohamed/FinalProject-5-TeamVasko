package Scalable.Command;

import Scalable.CommentCommand;
import Scalable.Services.CommentService;

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

