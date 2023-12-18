package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Teacher;



@Repository
public interface TeacherRepository extends JpaRepository <Teacher, Long>{
    
    List<Teacher> findByGender(String string);
    Optional<Teacher> findById(String id);
}
