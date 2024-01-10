package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    // sivlong new update week06
    @Override
    public User validateUser(String userID, String password) {
        User user = userRepository.findByUserID(userID);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // If the user is found and the password matches
            return user;
        }
        return null; // Return null if user not found or password doesn't match
    }

    // reset password user
    @Override
    public User getUserById(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user1 = null;
        if (optionalUser.isPresent()) {
            user1 = optionalUser.get();
        } else {
            throw new RuntimeException(" User not found for id :: " + id);
        }
        return user1;
    }

    
}