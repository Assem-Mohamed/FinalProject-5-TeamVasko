package com.example.CommentLogService.Repositories;

import com.example.CommentLogService.Models.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findByTaskIdAndParentCommentIdIsNull(Long taskId);


    List<Comment> findByParentCommentId(String parentCommentId);


    List<Comment> findByTaskId(Long taskId);
}
