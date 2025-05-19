package com.example.CommentLogService.Clients;

import org.example.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "user-service",url = "localhost:8080/api/user") //mynf3sh yob2a hard coded lazem ytbasa mn enviroment var el app prop
public interface UserClient {

    @GetMapping("/by-username/{username}")
    Optional<UserDTO> getUserByUsername(@PathVariable("username") String username);
}
