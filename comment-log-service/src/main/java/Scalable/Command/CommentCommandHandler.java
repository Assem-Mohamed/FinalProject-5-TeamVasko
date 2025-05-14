package Scalable.Command;

import Scalable.CommentCommand;
import Scalable.Services.CommentService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentCommandHandler {


    private final CommentService commentService;

    @Autowired
    public CommentCommandHandler(CommentService commentService) {
        this.commentService = commentService;
    }

    @RabbitListener(queues = "comment.queue")
    public void handleCommentCommand(CommentCommand command) {
        command.execute(commentService);
    }
}

