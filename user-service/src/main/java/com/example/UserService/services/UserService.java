package com.example.UserService.services;

import com.example.UserService.Clients.SearchClient;
import com.example.UserService.auth.strategy.AuthStrategy;
import com.example.UserService.models.User;
import com.example.UserService.rabbitmq.CommentMessage;
import com.example.UserService.rabbitmq.RabbitMQProducer;
import com.example.UserService.repositories.UserRepository;
import org.example.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static UserService instance;

    private final UserRepository userRepository;
    private final AuthStrategy authStrategy;

    @Autowired
    private SearchClient searchClient;

    @Autowired
    private RabbitMQProducer rabbitMQProducer;


    private UserService(UserRepository userRepository, AuthStrategy authStrategy) {
        this.userRepository = userRepository;
        this.authStrategy = authStrategy;
    }

    public static UserService getInstance(UserRepository repo, AuthStrategy strategy) {
        if (instance == null) {
            instance = new UserService(repo, strategy);
        }
        return instance;
    }

    public boolean login(String email, String password) {
        if (authStrategy.authenticate(email, password)) {
            User user = userRepository.findByEmail(email).get();
            user.setLoggedIn(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public void logout(String email) {
        userRepository.findByEmail(email).ifPresent(user -> {
            user.setLoggedIn(false);
            userRepository.save(user);
        });
    }

    public boolean resetPassword(String email, String newPassword) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setPassword(newPassword);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public Long getUserIdByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(User::getId)
                .orElse(null);
    }


    public void addComment(Long taskId, Long authorId, String content, String parentCommentId, List<Long> taggedUserIds) {
        CommentMessage message = new CommentMessage(
                taskId,
                authorId,
                content,
                Instant.now(),
                parentCommentId,
                taggedUserIds
        );
        rabbitMQProducer.sendComment(message);
    }

    public Optional<UserDTO> getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getPassword()));
    }



    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword()); // ideally hashed
        userRepository.save(user);
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
    }


    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getPassword()))
                .toList();
    }


    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getPassword()));
    }


    public Optional<UserDTO> updateUser(Long id, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            // Update password if needed
            userRepository.save(user);
            return Optional.of(new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getPassword()));
        }
        return Optional.empty();
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }


}

