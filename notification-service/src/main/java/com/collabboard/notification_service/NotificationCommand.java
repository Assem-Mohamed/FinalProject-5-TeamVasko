package com.collabboard.notification_service;


import com.collabboard.notification_service.Services.NotificationService;

import java.io.Serializable;

public interface NotificationCommand extends Serializable {
    void execute(NotificationService service);
}

