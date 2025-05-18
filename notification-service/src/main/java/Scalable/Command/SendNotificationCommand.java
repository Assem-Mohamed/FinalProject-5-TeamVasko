package Scalable.Command;

import Scalable.NotificationCommand;
import Scalable.Services.NotificationService;

public class SendNotificationCommand implements NotificationCommand {
    private final Long userId;
    private final String message;

    public SendNotificationCommand(Long userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    @Override
    public void execute(NotificationService notificationService) {
        notificationService.sendNotification(userId, message);
    }
}
