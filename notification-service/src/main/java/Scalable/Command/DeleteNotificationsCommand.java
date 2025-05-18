package Scalable.Command;

import Scalable.NotificationCommand;
import Scalable.Services.NotificationService;

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

