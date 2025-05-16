package com.collabboard.user_service.controllers;


import com.collabboard.search_reminder_service.models.SearchRequest;
import com.collabboard.user_service.Clients.SearchClient;
import com.collabboard.user_service.Clients.TaskClient;
import com.collabboard.user_service.auth.strategy.AuthStrategy;
import com.collabboard.user_service.repositories.UserRepository;
import com.collabboard.user_service.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.example.TaskDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;


    @Autowired private TaskClient taskClient;
    @Autowired private SearchClient searchClient;

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
    public ResponseEntity<String> login(@RequestBody Map<String, String> request, HttpSession session) {
        String email = request.get("email");
        String password = request.get("password");

        boolean success = userService.login(email, password);
        if (success) {
            Long userId = userService.getUserIdByEmail(email); // implement this method
            session.setAttribute("userId", userId); // Store user ID in session
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // Ends the session

        return ResponseEntity.ok("Logged out successfully");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request) {
        boolean success = userService.resetPassword(request.get("email"), request.get("newPassword"));
        return success ? ResponseEntity.ok("Password reset successful") : ResponseEntity.badRequest().body("User not found");
    }


    @PostMapping("/search")
    public List<TaskDTO> searchTasks(@RequestBody String keyword, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        //if (userId == null) throw new UnauthorizedException();

        List<TaskDTO> tasks = taskClient.getTasksByUserId(userId);

        SearchRequest request = new SearchRequest();
        request.setFullText(keyword);
        request.setUserId(userId);
        request.setTasks(tasks);

        return searchClient.searchUserTasks(request);
    }

}
