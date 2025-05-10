package com.collabboard.user_service.auth.strategy;

public interface AuthStrategy {
    boolean authenticate(String email, String password);
}

