package Scalable.rabbitmq;

import Scalable.Models.Comment;
import Scalable.Repositories.CommentRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CommentConsumer {
    private final CommentRepository commentRepository;

    public CommentConsumer(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @RabbitListener(queues = RabbitMQConfig.COMMENT_QUEUE) // e.g., "comment_queue"
    public void consumeComment(CommentMessage message) {
        Comment comment = new Comment(
                message.getTaskId(),
                message.getAuthorId(),
                message.getContent(),
                message.getCreatedAt()
        );
        comment.setParentCommentId(message.getParentCommentId());
        comment.setTaggedUserIds(message.getTaggedUserIds());

        commentRepository.save(comment);
        System.out.println("✅ Comment saved for taskId: " + message.getTaskId());
    }
}
