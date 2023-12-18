package com.example.demo.service;

import com.example.demo.model.Subject;
import java.util.List;
public interface SubjectService {
    List<Subject> getAllSubjects();
    void saveSubject(Subject subject);
    
} 