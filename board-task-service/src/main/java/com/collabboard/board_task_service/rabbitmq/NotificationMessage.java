package com.collabboard.board_task_service.rabbitmq;

public class NotificationMessage {
    private Long userId;
    private String message;

    public NotificationMessage() {}

    public NotificationMessage(Long userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
