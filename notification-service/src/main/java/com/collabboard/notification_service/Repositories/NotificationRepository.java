package com.collabboard.notification_service.Repositories;

import com.collabboard.notification_service.Models.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {

    List<Notification> findByUserId(Long userId);

    List<Notification> findByUserIdAndReadFalse(Long userId);

    void deleteByUserId(Long userId);
}

