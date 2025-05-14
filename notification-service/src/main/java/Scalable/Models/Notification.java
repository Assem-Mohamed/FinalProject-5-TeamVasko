package Scalable.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "notifications")
public class Notification {
    @Id
    private String id;

    private String userId;
    private String message;
    private boolean read;
    private Date timestamp;

    public Notification(String userId, String message, boolean read, Date timestamp) {
        this.userId = userId;
        this.message = message;
        this.read = read;
        this.timestamp = timestamp;
    }

    public Notification(String userId, String message) {
        this.userId = userId;
        this.message = message;
        this.read = false;
        this.timestamp = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}

