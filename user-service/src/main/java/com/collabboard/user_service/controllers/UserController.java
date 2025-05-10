package com.collabboard.user_service.controllers;

import com.collabboard.user_service.auth.strategy.AuthStrategy;
import com.collabboard.user_service.repositories.UserRepository;
import com.collabboard.user_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, AuthStrategy authStrategy) {
        this.userService = UserService.getInstance(userRepository, authStrategy);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> request) {
        boolean success = userService.login(request.get("email"), request.get("password"));
        return success ? ResponseEntity.ok("Login successful") : ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody Map<String, String> request) {
        userService.logout(request.get("email"));
        return ResponseEntity.ok("Logged out successfully");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request) {
        boolean success = userService.resetPassword(request.get("email"), request.get("newPassword"));
        return success ? ResponseEntity.ok("Password reset successful") : ResponseEntity.badRequest().body("User not found");
    }
}
