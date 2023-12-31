package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

public interface UserService {

    List<User> getAllUsers();
    void saveUser(User user);
    User validateUser(String userID, String password);
} 