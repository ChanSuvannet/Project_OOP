package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    List<Student> findByGender(String stu_gender);
    Student findByNameid(String nameid);
} 
