package com.collabboard.notification_service.Command;


import com.collabboard.notification_service.Models.Notification;
import com.collabboard.notification_service.NotificationCommand;
import com.collabboard.notification_service.Services.NotificationService;

public class MarkAsReadCommand implements NotificationCommand<Notification> {

    private final NotificationService notificationService;
    private final String notificationId;

    public MarkAsReadCommand(NotificationService notificationService, String id) {
        this.notificationService = notificationService;
        this.notificationId = id;
    }

    @Override
    public Notification execute() {
        return notificationService.markAsRead(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
    }
}

