package com.example.app.repository;

import org.springframework.stereotype.Repository;
import com.example.app.models.User;
import java.util.HashMap;

@Repository
public class UserRepository {
    private final HashMap<String, String> users = new HashMap<>();

    public void save(String username, String password) {
        users.put(username, password);
    }

    public String findPasswordByUsername(String username) {
        return users.get(username);
    }

    public boolean existsByUsername(String username) {
        return users.containsKey(username);
    }
}
