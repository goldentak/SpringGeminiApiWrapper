package com.example.app.services;

import com.example.app.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(String username, String password) {
        userRepository.save(username, password);
    }
    public boolean login(String username, String password) {
        return userRepository.existsByUsername(username) && password.equals(userRepository.findPasswordByUsername(username));
    }
}
