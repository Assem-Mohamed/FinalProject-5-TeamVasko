package com.example.CommentLogService.Controllers;

import com.example.CommentLogService.Command.*;
import com.example.CommentLogService.CommentCommand;
import com.example.CommentLogService.Models.Comment;
import com.example.CommentLogService.Services.CommentService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommandDispatcher commandDispatcher;
    private final CommentService commentService;

    @Autowired
    public CommentController(CommandDispatcher commandDispatcher, CommentService commentService) {
        this.commandDispatcher = commandDispatcher;
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        comment.setId(new ObjectId().toString());
        CommentCommand command = new CreateCommentCommand(comment);
        commandDispatcher.dispatch("comment.exchange", "comment.routingKey", command);
        return ResponseEntity.accepted().body(comment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable String id) {
        Comment comment = commentService.getCommentById(id);
        if (comment != null) {
            return ResponseEntity.ok(comment);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Comment>> getCommentsByTaskId(@PathVariable Long taskId) {
        return ResponseEntity.ok(commentService.getCommentsByTaskId(taskId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable String id, @RequestBody Comment updatedComment) {
        CommentCommand command = new UpdateCommentCommand(id, updatedComment);
        commandDispatcher.dispatch("comment.exchange", "comment.routingKey", command);
        return ResponseEntity.ok(commentService.updateComment(id, updatedComment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable String id) {
        CommentCommand command = new DeleteCommentCommand(id);
        commandDispatcher.dispatch("comment.exchange", "comment.routingKey", command);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/pin")
    public ResponseEntity<Comment> pinComment(@PathVariable String id) {
        CommentCommand command = new PinCommentCommand(id);
        commandDispatcher.dispatch("comment.exchange", "comment.routingKey", command);
        Comment comment = commentService.pinComment(id);
        if (comment != null) return ResponseEntity.ok(comment);
        else return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/unpin")
    public ResponseEntity<Comment> unpinComment(@PathVariable String id) {
        CommentCommand command = new UnpinCommentCommand(id);
        commandDispatcher.dispatch("comment.exchange", "comment.routingKey", command);
        Comment comment = commentService.unpinComment(id);
        if (comment != null) return ResponseEntity.ok(comment);
        else return ResponseEntity.notFound().build();
    }

    @GetMapping("/task/{taskId}/threaded")
    public ResponseEntity<List<Comment>> getThreadedComments(@PathVariable Long taskId) {
        // Note: optionally make this async only if needed
        return ResponseEntity.ok(commentService.getThreadedComments(taskId));
    }


}
