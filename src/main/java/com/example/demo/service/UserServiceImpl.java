package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Teacher;
import com.example.demo.model.User;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    
    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user){
        
        userRepository.save(user);
    }

    @Override
    public User validateUser(String userID, String password){
        User user = userRepository.findByUserID(userID);
        if(user != null && user.getPassword().equals(password)){
            return user;
        }
        return null;
    }

}
