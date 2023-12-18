package com.example.demo.service;

import com.example.demo.model.Teacher;

import java.util.List; // Import the correct List interface

public interface TeacherService {
    List<Teacher> getAllTeachers();
    void saveTeacher(Teacher teacher);
    Teacher getTeacherByNumber(long number);
    void deletedTeacherByNumber(long number);
    int getTotalTeachers();
    int getTotalMaleTeachers(); // New method declaration for total male teachers
    int getTotalFemaleTeachers(); // New method declaration for total female teachers
}
