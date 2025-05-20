package com.example.UserService.controllers;

import com.example.UserService.Clients.SearchClient;
import com.example.UserService.Clients.TaskClient;
import com.example.UserService.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.example.TaskDTO;
import org.example.SearchDTO;

import org.example.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;


    @Autowired private TaskClient taskClient;
    @Autowired private SearchClient searchClient;

//    @Autowired
//    public UserController(UserRepository userRepository, AuthStrategy authStrategy) {
//        this.userService = UserService.getInstance(userRepository, authStrategy);
//    }
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
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

//    public List<TaskDTO> searchTasks(@RequestBody Map<String, String> request, HttpSession session) {
//        String keyword = request.get("keyword");

    @PostMapping("/search")
    public List<TaskDTO> searchTasks(@RequestBody Map<String, String> request, HttpSession session) {
        String keyword = request.get("keyword");
        Long userId = (Long) session.getAttribute("userId");

        List<TaskDTO> tasks = taskClient.getTasksByAssignee(userId);

        // Use the new SearchDTO
        SearchDTO searchDTO = new SearchDTO();
        searchDTO.setFullText(keyword);
        searchDTO.setUserId(userId);
        searchDTO.setTasks(tasks);

        return searchClient.searchUserTasks(searchDTO);
    }


    @PostMapping("/comments")
    public ResponseEntity<String> submitComment(@RequestBody Map<String, Object> requestBody) {
        Long taskId = ((Number) requestBody.get("taskId")).longValue();
        Long authorId = ((Number) requestBody.get("authorId")).longValue();
        String content = (String) requestBody.get("content");
        String parentCommentId = (String) requestBody.get("parentCommentId");
        List<Integer> taggedIdsRaw = (List<Integer>) requestBody.getOrDefault("taggedUserIds", List.of());
        List<Long> taggedUserIds = taggedIdsRaw.stream().map(Long::valueOf).toList();

        userService.addComment(taskId, authorId, content, parentCommentId, taggedUserIds);
        return ResponseEntity.ok("Comment sent to comment-service");
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        Optional<UserDTO> user = userService.getUserByUsername(username);
        return user.map(u -> ResponseEntity.ok(new UserDTO(u.getId(), u.getUsername(), u.getEmail(), u.getPassword())))
                .orElse(ResponseEntity.notFound().build());
    }


    // CRUDS
    // CREATE a new user
    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    // READ all users
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // READ user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Optional<UserDTO> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE user by ID
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO updatedUserDTO) {
        Optional<UserDTO> updatedUser = userService.updateUser(id, updatedUserDTO);
        return updatedUser.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        return deleted ? ResponseEntity.ok("User deleted successfully")
                : ResponseEntity.status(404).body("User not found");
    }


}
