package com.collabboard.board_task_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BoardAndTaskServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardAndTaskServiceApplication.class, args);
	}

}

