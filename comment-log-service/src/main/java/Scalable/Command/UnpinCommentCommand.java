package Scalable.Command;


import Scalable.CommentCommand;
import Scalable.Services.CommentService;

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

