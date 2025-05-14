package Scalable.Command;

import Scalable.NotificationCommand;
import Scalable.Services.NotificationService;

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

