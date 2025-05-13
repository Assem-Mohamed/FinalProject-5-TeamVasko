package Scalable.Command;

import Scalable.NotificationCommand;
import Scalable.Services.NotificationService;

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
