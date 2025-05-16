package com.collabboard.user_service.rabbitmq;

import com.collabboard.user_service.rabbitmq.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendComment(CommentMessage message) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.COMMENT_ROUTING_KEY,
                message
        );

        System.out.println("📬 Sent Notification via RabbitMQ to user " + message.getRecipientId() + ": " + message.getMessage());
    }
}
