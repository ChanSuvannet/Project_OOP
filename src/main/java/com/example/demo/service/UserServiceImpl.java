package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private JavaMailSender mailSender;

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
    // send email to uer 
    @Override
    public User sendEmailToUser(String email) {
        Optional<User> optionalEmail = userRepository.findByEmail(email);
        User user2 = null;
        if (optionalEmail.isPresent()) {
            user2 = optionalEmail.get();

            // Create a SimpleMailMessage.
            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(user2.getEmail());
            message.setSubject("Reset Your password");
            message.setText("Hello,! You can reset your password at http://localhost:8080/User/"+ user2.getUserID());
            // Send message
            mailSender.send(message);

        } else {
            throw new RuntimeException(" User not found for This Email :: " + email);
        }
        return user2;
    }
}