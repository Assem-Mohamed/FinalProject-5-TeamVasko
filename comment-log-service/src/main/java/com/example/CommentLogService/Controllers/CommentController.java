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

    private final CommentService commentService;
    private final CommandExecutor commandExecutor;


    public CommentController(CommentService commentService, CommandExecutor commandExecutor) {
        this.commentService = commentService;
        this.commandExecutor = commandExecutor;
    }

    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        CreateCommentCommand command = new CreateCommentCommand(commentService, comment);
        return commandExecutor.execute(command);
    }

    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable String id, @RequestBody Comment updatedComment) {
        UpdateCommentCommand command = new UpdateCommentCommand(commentService, id, updatedComment);
        return commandExecutor.execute(command);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable String id) {
        DeleteCommentCommand command = new DeleteCommentCommand(commentService, id);
        commandExecutor.execute(command);
    }

    @GetMapping("/task/{taskId}")
    public List<Comment> getCommentsByTaskId(@PathVariable Long taskId) {
        return commentService.getCommentsByTaskId(taskId); // not using command pattern here, could be added
    }

    @GetMapping("/threaded/{taskId}")
    public List<Comment> getThreadedComments(@PathVariable Long taskId) {
        GetThreadedCommentsCommand command = new GetThreadedCommentsCommand(commentService, taskId);
        return commandExecutor.execute(command);
    }

    @PostMapping("/{id}/pin")
    public Comment pinComment(@PathVariable String id) {
        PinCommentCommand command = new PinCommentCommand(commentService, id);
        return commandExecutor.execute(command);
    }

    @PostMapping("/{id}/unpin")
    public Comment unpinComment(@PathVariable String id) {
        UnpinCommentCommand command = new UnpinCommentCommand(commentService, id);
        return commandExecutor.execute(command);
    }

}