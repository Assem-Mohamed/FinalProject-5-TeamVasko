package com.collabboard.notification_service.Command;

import com.collabboard.notification_service.NotificationCommand;
import com.collabboard.notification_service.Services.NotificationService;

public class MarkAsReadCommand implements NotificationCommand {
    private final String notificationId;

    public MarkAsReadCommand(String notificationId) {
        this.notificationId = notificationId;
    }

    @Override
    public void execute(NotificationService notificationService) {
        notificationService.markAsRead(notificationId);
    }
}
