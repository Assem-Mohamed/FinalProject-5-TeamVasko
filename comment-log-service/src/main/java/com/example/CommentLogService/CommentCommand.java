package com.example.CommentLogService;

import com.example.CommentLogService.Services.CommentService;

import java.io.Serializable;

public interface CommentCommand<R> {
    R execute();
}

