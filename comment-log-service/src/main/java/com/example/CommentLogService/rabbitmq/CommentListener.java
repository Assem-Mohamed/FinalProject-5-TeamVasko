package com.example.CommentLogService.rabbitmq;




import com.example.CommentLogService.Command.CommandExecutor;
import com.example.CommentLogService.Command.CreateCommentCommand;
import com.example.CommentLogService.Dtos.CommentMessageDTO;
import com.example.CommentLogService.Models.Comment;
import com.example.CommentLogService.Services.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommentListener {

    private final ObjectMapper objectMapper;
    private final CommentService commentService;
    private final CommandExecutor commandExecutor;

    public CommentListener(ObjectMapper objectMapper,
                           CommentService commentService,
                                CommandExecutor commandExecutor) {
        this.objectMapper = objectMapper;
        this.commentService = commentService;
        this.commandExecutor = commandExecutor;
    }

    @RabbitListener(queues = "comment_queue")
    public void listen(String message) {
        try {
            CommentMessageDTO dto = objectMapper.readValue(message, CommentMessageDTO.class);

            Comment comment = new Comment();
            comment.setAuthorId(dto.getAuthorId());
            comment.setTaskId(dto.getTaskId());
            comment.setContent(dto.getContent());
            comment.setCreatedAt(dto.getCreatedAt());
            comment.setParentCommentId(dto.getParentCommentId());


            CreateCommentCommand command = new CreateCommentCommand(commentService, comment);
            commandExecutor.execute(command);

            System.out.println("Comment added");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


