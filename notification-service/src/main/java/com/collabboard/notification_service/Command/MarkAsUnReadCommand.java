package com.collabboard.notification_service.Command;

import com.collabboard.notification_service.Models.Notification;
import com.collabboard.notification_service.NotificationCommand;
import com.collabboard.notification_service.Services.NotificationService;

public class MarkAsUnReadCommand implements NotificationCommand<Notification> {
    private final String notificationId;
    private final NotificationService notificationService;

    public MarkAsUnReadCommand(NotificationService notificationService,String notificationId) {
        this.notificationId = notificationId;
        this.notificationService = notificationService;
    }

    @Override
    public Notification execute() {

        return notificationService.markAsUnread(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
    }
}


