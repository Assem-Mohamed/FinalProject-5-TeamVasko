package com.example.CommentLogService.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Document(collection = "comments")
public class Comment implements Serializable {
        private static final long serialVersionUID = 1L;

        @Id
        private String id;
        private Long taskId;
        private Long authorId;
        private String content;
        private Instant createdAt;
        private boolean pinned;
        private List<Comment> replies;
        private String parentCommentId;
        private List<Long> taggedUserIds;

        public Comment() {
        }
        public Comment(Long taskId, Long authorId, String content, Instant createdAt) {
                this.taskId = taskId;
                this.authorId = authorId;
                this.content = content;
                this.createdAt = createdAt;
                this.pinned = false;
                this.parentCommentId = null;
        }

        public Comment(String id, Long taskId, Long authorId, String content, Instant createdAt, boolean pinned, List<Comment> replies, String parentCommentId, List<Long> taggedUserIds) {
                this.id = id;
                this.taskId = taskId;
                this.authorId = authorId;
                this.content = content;
                this.createdAt = createdAt;
                this.pinned = pinned;
                this.replies = replies;
                this.parentCommentId = parentCommentId;
                this.taggedUserIds = taggedUserIds;
        }


        public void setId(String id) {
                this.id = id;
        }

        public void setTaskId(Long taskId) {
                this.taskId = taskId;
        }

        public void setAuthorId(Long authorId) {
                this.authorId = authorId;
        }

        public void setContent(String content) {
                this.content = content;
        }

        public void setCreatedAt(Instant createdAt) {
                this.createdAt = createdAt;
        }

        public void setPinned(boolean pinned) {
                this.pinned = pinned;
        }

        public void setReplies(List<Comment> replies) {
                this.replies = replies;
        }

        public void setParentCommentId(String parentCommentId) {
                this.parentCommentId = parentCommentId;
        }

        public String getId() {
                return id;
        }

        public Long getTaskId() {
                return taskId;
        }

        public Long getAuthorId() {
                return authorId;
        }

        public String getContent() {
                return content;
        }

        public Instant getCreatedAt() {
                return createdAt;
        }

        public boolean isPinned() {
                return pinned;
        }

        public List<Comment> getReplies() {
                return replies;
        }

        public String getParentCommentId() {
                return parentCommentId;
        }

        public List<Long> getTaggedUserIds() {
                return taggedUserIds;
        }

        public void setTaggedUserIds(List<Long> taggedUserIds) {
                this.taggedUserIds = taggedUserIds;
        }
}
