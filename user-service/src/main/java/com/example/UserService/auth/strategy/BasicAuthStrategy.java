package com.example.UserService.auth.strategy;

import com.example.UserService.models.User;
import com.example.UserService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BasicAuthStrategy implements AuthStrategy {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean authenticate(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent() && user.get().getPassword().equals(password);
    }
}
