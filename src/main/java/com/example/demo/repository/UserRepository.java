package com.example.demo.repository;

import java.util.Optional;

import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Teacher;
import com.example.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserID(String userID);
}
