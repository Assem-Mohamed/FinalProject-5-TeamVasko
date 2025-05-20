package com.example.CommentLogService.Command;


import com.example.CommentLogService.CommentCommand;
import com.example.CommentLogService.Models.Comment;
import com.example.CommentLogService.rabbitmq.CommentMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandDispatcher {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public CommandDispatcher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void dispatch(String exchange, String routingKey, Comment commandPayload) {
        rabbitTemplate.convertAndSend(exchange, routingKey, commandPayload);
    }
}




