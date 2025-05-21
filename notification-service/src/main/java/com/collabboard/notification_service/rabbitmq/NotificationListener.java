package com.collabboard.notification_service.rabbitmq;


import com.collabboard.notification_service.Command.SendNotificationCommand;
import com.collabboard.notification_service.Command.CommandExecutor;
import com.collabboard.notification_service.Dtos.NotificationMessageDTO;
import com.collabboard.notification_service.Services.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    private final ObjectMapper objectMapper;
    private final NotificationService notificationService;
    private final CommandExecutor commandExecutor;

    public NotificationListener(ObjectMapper objectMapper,
                                NotificationService notificationService,
                                CommandExecutor commandExecutor) {
        this.objectMapper = objectMapper;
        this.notificationService = notificationService;
        this.commandExecutor = commandExecutor;
    }

    @RabbitListener(queues = "notification_queue")
    public void listen(String message) {
        System.out.println("Received notification message: " + message);
        try {
            NotificationMessageDTO dto = objectMapper.readValue(message, NotificationMessageDTO.class);

            // Execute command
            SendNotificationCommand command = new SendNotificationCommand(notificationService, dto.getUserId(), dto.getMessage());
            commandExecutor.execute(command);

            System.out.println("Notification processed for user: " + dto.getUserId());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

