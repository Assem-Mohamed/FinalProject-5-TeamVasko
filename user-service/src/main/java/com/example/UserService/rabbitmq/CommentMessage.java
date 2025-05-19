package com.example.UserService.rabbitmq;

import java.time.Instant;
import java.util.List;

public class CommentMessage {
    private Long taskId;
    private Long authorId;
    private String content;
    private Instant createdAt;
    private String parentCommentId;
    private List<Long> taggedUserIds;

    public CommentMessage() {
    }

    public CommentMessage(Long taskId, Long authorId, String content, Instant createdAt, String parentCommentId, List<Long> taggedUserIds) {
        this.taskId = taskId;
        this.authorId = authorId;
        this.content = content;
        this.createdAt = createdAt;
        this.parentCommentId = parentCommentId;
        this.taggedUserIds = taggedUserIds;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(String parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public List<Long> getTaggedUserIds() {
        return taggedUserIds;
    }

    public void setTaggedUserIds(List<Long> taggedUserIds) {
        this.taggedUserIds = taggedUserIds;
    }
}