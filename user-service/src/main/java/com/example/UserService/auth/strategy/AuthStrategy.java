package com.example.UserService.auth.strategy;

public interface AuthStrategy {
    boolean authenticate(String email, String password);
}

