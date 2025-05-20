package com.example.CommentLogService.Dtos;


import java.time.Instant;

public class CommentMessageDTO {
    private Long taskId;
    private Long authorId;
    private String content;
    private Instant createdAt;
    private String parentCommentId;


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
//    public boolean isPinned() {
//        return pinned;
//    }
//
//    public void setPinned(boolean pinned) {
//        this.pinned = pinned;
//    }
}

