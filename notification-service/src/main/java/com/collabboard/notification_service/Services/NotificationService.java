package com.collabboard.notification_service.Services;

import com.collabboard.notification_service.Models.Notification;
import com.collabboard.notification_service.Repositories.NotificationRepository;
import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    final private NotificationRepository notificationRepository;

    MongoClient mongoClient;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, MongoClient mongoClient) {
        this.notificationRepository = notificationRepository;
        this.mongoClient = mongoClient;
    }



    public Notification sendNotification(Long userId, String message) {
        Notification notification = new Notification(userId, message, false, new Date());
        return notificationRepository.save(notification);
    }

    public List<Notification> getNotificationsForUser(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    public List<Notification> getUnreadNotifications(Long userId) {
        return notificationRepository.findByUserIdAndReadFalse(userId);
    }

    public Optional<Notification> markAsRead(String notificationId) {
        Optional<Notification> optional = notificationRepository.findById(notificationId);
        optional.ifPresent(notification -> {
            notification.setRead(true);
            notificationRepository.save(notification);
        });
        return optional;
    }

    public Optional<Notification> markAsUnread(String notificationId) {
        Optional<Notification> optional = notificationRepository.findById(notificationId);
        optional.ifPresent(notification -> {
            notification.setRead(false);
            notificationRepository.save(notification);
        });
        return optional;
    }

    public void deleteNotificationsForUser(Long userId) {
        notificationRepository.deleteByUserId(userId);
    }
}

