package com.collabboard.notification_service.Command;


import com.collabboard.notification_service.NotificationCommand;
import com.collabboard.notification_service.Services.NotificationService;

public class DeleteNotificationsCommand implements NotificationCommand<Void> {

    private final NotificationService notificationService;
    private final Long userId;

    public DeleteNotificationsCommand(NotificationService notificationService, Long userId) {
        this.notificationService = notificationService;
        this.userId = userId;
    }

    @Override
    public Void execute() {
        notificationService.deleteNotificationsForUser(userId);
        return null;
    }
}


