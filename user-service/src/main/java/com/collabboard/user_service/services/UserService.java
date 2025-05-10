package com.collabboard.user_service.services;

import com.collabboard.user_service.auth.strategy.AuthStrategy;
import com.collabboard.user_service.models.User;
import com.collabboard.user_service.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static UserService instance;

    private final UserRepository userRepository;
    private final AuthStrategy authStrategy;

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
}

