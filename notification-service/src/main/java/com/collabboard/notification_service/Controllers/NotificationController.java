package com.collabboard.notification_service.Controllers;

import com.collabboard.notification_service.Command.*;
import com.collabboard.notification_service.Models.Notification;
import com.collabboard.notification_service.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final CommandExecutor commandExecutor;

    @Autowired
    public NotificationController(NotificationService notificationService,
                                  CommandExecutor commandExecutor) {
        this.notificationService = notificationService;
        this.commandExecutor = commandExecutor;
    }

    @PostMapping("/send")
    public Notification sendNotification(@RequestParam Long userId, @RequestParam String message) {
        SendNotificationCommand command = new SendNotificationCommand(notificationService, userId, message);
        return commandExecutor.execute(command);
    }

    @GetMapping("/{userId}")
    public List<Notification> getAll(@PathVariable Long userId) {
        return notificationService.getNotificationsForUser(userId); // optional: wrap in command
    }

    @GetMapping("/{userId}/unread")
    public List<Notification> getUnread(@PathVariable Long userId) {
        return notificationService.getUnreadNotifications(userId); // optional: wrap in command
    }

    @PutMapping("/{id}/read")
    public Notification markAsRead(@PathVariable String id) {
        MarkAsReadCommand command = new MarkAsReadCommand(notificationService, id);
        return commandExecutor.execute(command);
    }

    @PutMapping("/{id}/unread")
    public Notification markAsUnread(@PathVariable String id) {
        MarkAsUnReadCommand command = new MarkAsUnReadCommand(notificationService, id);
        return commandExecutor.execute(command);
    }

    @DeleteMapping("/{userId}")
    public void deleteAllForUser(@PathVariable Long userId) {
        DeleteNotificationsCommand command = new DeleteNotificationsCommand(notificationService, userId);
        commandExecutor.execute(command);
    }
}
