package com.example.UserService.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendComment(CommentMessage commentMessage) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.COMMENT_ROUTING_KEY,
                commentMessage
        );

        System.out.println("📤 Sent comment to queue for taskId: " + commentMessage.getTaskId());
    }
}
