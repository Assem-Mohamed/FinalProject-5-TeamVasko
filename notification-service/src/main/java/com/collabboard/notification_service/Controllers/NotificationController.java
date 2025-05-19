package com.collabboard.notification_service.Controllers;

import com.collabboard.notification_service.Command.*;
import com.collabboard.notification_service.Models.Notification;
import com.collabboard.notification_service.NotificationCommand;
import com.collabboard.notification_service.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final CommandDispatcher commandDispatcher;
    private final NotificationService notificationService;

    @Autowired
    public NotificationController(CommandDispatcher commandDispatcher,
                                  NotificationService notificationService) {
        this.commandDispatcher = commandDispatcher;
        this.notificationService = notificationService;
    }

    @GetMapping
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("Notifications endpoint is alive");
    }

    @PostMapping("/send")
    public ResponseEntity<Void> sendNotification(@RequestParam Long userId, @RequestParam String message) {
        NotificationCommand command = new SendNotificationCommand(userId, message);
        commandDispatcher.dispatch("notification.exchange", "notification.routingKey", command);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Notification>> getAll(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getNotificationsForUser(userId));
    }

    @GetMapping("/{userId}/unread")
    public ResponseEntity<List<Notification>> getUnread(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getUnreadNotifications(userId));
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<Notification> markAsRead(@PathVariable String id) {
        NotificationCommand command = new MarkAsReadCommand(id);
        commandDispatcher.dispatch("notification.exchange", "notification.routingKey", command);
        return notificationService.markAsRead(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Notification not found"));
    }

    @PutMapping("/{id}/unread")
    public ResponseEntity<Notification> markAsUnread(@PathVariable String id) {
        NotificationCommand command = new MarkAsUnReadCommand(id);
        commandDispatcher.dispatch("notification.exchange", "notification.routingKey", command);
        return notificationService.markAsUnread(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Notification not found"));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteAllForUser(@PathVariable Long userId) {
        NotificationCommand command = new DeleteNotificationsCommand(userId);
        commandDispatcher.dispatch("notification.exchange", "notification.routingKey", command);
        return ResponseEntity.noContent().build();
    }
}
