package Scalable.rabbitmq;

import Scalable.Services.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    private final NotificationService notificationService;

    public NotificationConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = RabbitMQConfig.NOTIFICATION_QUEUE)
    public void consume(NotificationMessage message) {
        notificationService.sendNotification(message.getRecipientId(), message.getMessage());
        System.out.println("✅ Notification saved for user: " + message.getRecipientId());
    }
}