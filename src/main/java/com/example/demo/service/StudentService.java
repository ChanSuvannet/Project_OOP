package com.example.demo.service;

import com.example.demo.model.Student;
import java.util.List;


public interface StudentService {
    List<Student> getAllStudents();
    void saveStudent(Student student);
    int TotalStudents();
    int TotalFemaleStudents();
    int TotalMaleStudents();
    Student getStudentById(Long id);
} 