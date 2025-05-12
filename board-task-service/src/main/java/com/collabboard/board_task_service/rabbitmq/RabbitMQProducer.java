package com.collabboard.board_task_service.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendNotification(NotificationMessage message) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.NOTIFICATION_ROUTING_KEY,
                message
        );

        System.out.println("📬 Sent Notification via RabbitMQ to user " + message.getRecipientId() + ": " + message.getMessage());
    }
}
