package com.collabboard.comment_log_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class
})
public class CommentLogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommentLogServiceApplication.class, args);
	}

}
