package com.collabboard.notification_service;



public interface NotificationCommand<R> {
    R execute();
}
