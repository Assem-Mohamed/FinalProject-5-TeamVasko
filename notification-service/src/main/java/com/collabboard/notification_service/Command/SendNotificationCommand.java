package com.collabboard.notification_service.Command;


import com.collabboard.notification_service.Models.Notification;
import com.collabboard.notification_service.NotificationCommand;
import com.collabboard.notification_service.Services.NotificationService;

public class SendNotificationCommand implements NotificationCommand<Notification> {

    private final NotificationService notificationService;
    private final Long userId;
    private final String message;

    public SendNotificationCommand(NotificationService service, Long userId, String message) {
        this.notificationService = service;
        this.userId = userId;
        this.message = message;
    }

    @Override
    public Notification execute() {
        return notificationService.sendNotification(userId, message);
    }
}

