package Scalable.Command;


import Scalable.CommentCommand;
import Scalable.Services.CommentService;

public class PinCommentCommand implements CommentCommand {
    private String commentId;

    public PinCommentCommand() {}

    public PinCommentCommand(String commentId) {
        this.commentId = commentId;
    }

    @Override
    public void execute(CommentService commentService) {
        commentService.pinComment(commentId);
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }
}

