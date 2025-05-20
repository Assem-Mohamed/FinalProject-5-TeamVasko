package com.example.CommentLogService.rabbitmq;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String COMMENT_QUEUE = "comment_queue";

    @Bean
    public Queue notificationQueue() {
        return new Queue(COMMENT_QUEUE, true); // durable
    }
}
