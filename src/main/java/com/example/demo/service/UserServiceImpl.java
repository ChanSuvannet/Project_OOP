package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    // password 1111 -> Encrypt (sivlong)
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
        RestTemplate restTemplate = new RestTemplate();
        String website = "https://api.telegram.org/bot" + BOT_TOKEN + "/sendMessage";
        String message = "<a href=\"www.localhost:8080/User/"+ user2.getId()  + "\"> Hello! You can reset your password here </a>";
        Map<String, String> params = new HashMap<>();
        params.put("chat_id", CHAT_ID);
        params.put("text", message);
        params.put("parse_mode", "HTML"); // Add this line
        restTemplate.postForObject(website, params, String.class);
    } else {
        throw new RuntimeException(" User not found for This Email :: " + email);
    }
    return user2;
}
    private static final String BOT_TOKEN = "6630897410:AAGeme9_he1baYsMhjgAwqNnzEVGI2mOD0g";
    private static final String CHAT_ID = "1099740653";

    // @Override
    // public String sendTelegram(String phone){
    //     RestTemplate restTemplate = new RestTemplate();
    //     String website = "https://api.telegram.org/bot" + BOT_TOKEN + "/sendMessage";
    //     String message = "Hello,! You can reset your password at http://localhost:8080/User/" + user2.getId();

    //     Map<String, String> params = new HashMap<>();
    //     params.put("chat_id", CHAT_ID);
    //     params.put("text", message);
    //     return restTemplate.postForObject(website, params, String.class);
    // }
}