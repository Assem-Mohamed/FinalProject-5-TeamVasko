package com.collabboard.search_reminder_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCaching
//@EnableFeignClients(basePackages = "com.collabboard.searchreminderservice.client")
@SpringBootApplication

public class SearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchServiceApplication.class, args);
	}

}
