package com.collabboard.notification_service;


import com.collabboard.notification_service.Services.NotificationService;

import java.io.Serializable;


public interface NotificationCommand<T> {
    T execute();
}


