package Scalable;


import Scalable.Services.NotificationService;

import java.io.Serializable;

public interface NotificationCommand extends Serializable {
    void execute(NotificationService service);
}

