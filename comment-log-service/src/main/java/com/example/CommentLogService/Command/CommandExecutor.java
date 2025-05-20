package com.example.CommentLogService.Command;

import com.example.CommentLogService.CommentCommand;
import org.springframework.stereotype.Component;

@Component
public class CommandExecutor {

    public <R> R execute(CommentCommand<R> command) {
        return command.execute();
    }
}

