package com.collabboard.notification_service.Command;

import com.collabboard.notification_service.NotificationCommand;
import com.collabboard.notification_service.Services.NotificationService;

public class MarkAsUnReadCommand implements NotificationCommand {
    private final String notificationId;

    public MarkAsUnReadCommand(String notificationId) {
        this.notificationId = notificationId;
    }

    @Override
    public void execute(NotificationService notificationService) {
        notificationService.markAsUnread(notificationId);
    }
}

