package com.collabboard.comment_log_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CommentLogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommentLogServiceApplication.class, args);
	}

}
