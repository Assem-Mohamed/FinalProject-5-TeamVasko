package com.collabboard.notification_service.Command;

import com.collabboard.notification_service.NotificationCommand;
import com.collabboard.notification_service.Services.NotificationService;

public class DeleteNotificationsCommand implements NotificationCommand {
    private final Long userId;

    public DeleteNotificationsCommand(Long userId) {
        this.userId = userId;
    }

    @Override
    public void execute(NotificationService notificationService) {
        notificationService.deleteNotificationsForUser(userId);
    }
}

