package Scalable.Command;

import Scalable.CommentCommand;
import Scalable.Services.CommentService;

public class GetThreadedCommentsCommand implements CommentCommand {
    private Long taskId;

    public GetThreadedCommentsCommand() {}

    public GetThreadedCommentsCommand(Long taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(CommentService commentService) {
        // This is a read operation, but we include it for consistency.
        commentService.getThreadedComments(taskId);
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}

