package Scalable;

import Scalable.Services.CommentService;

import java.io.Serializable;

public interface CommentCommand extends Serializable {
    void execute(CommentService commentService);
}

