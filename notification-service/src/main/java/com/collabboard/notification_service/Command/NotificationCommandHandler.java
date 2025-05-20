//package com.collabboard.notification_service.Command;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.collabboard.notification_service.NotificationCommand;
//import com.collabboard.notification_service.Services.NotificationService;
//
//@Component
//public class NotificationCommandHandler {
//
//    private final NotificationService notificationService;
//
//    @Autowired
//    public NotificationCommandHandler(NotificationService notificationService) {
//        this.notificationService = notificationService;
//    }
//
//    @RabbitListener(queues = "notification.queue")
//    public void handleNotificationCommand(NotificationCommand command) {
//        command.execute(notificationService);
//    }
//}
//
//
