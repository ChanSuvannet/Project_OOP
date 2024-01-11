package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserID(String userID);
    // User findByUserEmail(String email);
    Optional<User> findById(long id);
    Optional<User> findByEmail(String email);
}

