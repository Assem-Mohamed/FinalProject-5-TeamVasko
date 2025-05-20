package com.collabboard.notification_service.Command;

import com.collabboard.notification_service.NotificationCommand;
import org.springframework.stereotype.Component;

@Component
public class CommandExecutor {

    public <T> T execute(NotificationCommand<T> command) {
        return command.execute();
    }
}
