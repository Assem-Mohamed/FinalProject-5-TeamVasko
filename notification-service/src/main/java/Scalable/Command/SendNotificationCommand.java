package Scalable.Command;

import Scalable.NotificationCommand;
import Scalable.Services.NotificationService;

public class SendNotificationCommand implements NotificationCommand {
    private final String userId;
    private final String message;

    public SendNotificationCommand(String userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    @Override
    public void execute(NotificationService notificationService) {
        notificationService.sendNotification(userId, message);
    }
}
