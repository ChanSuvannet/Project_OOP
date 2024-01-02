package com.example.demo.service;

import com.example.demo.model.Subject;
import java.util.List;


public interface SubjectService {
    // function get and store for  all subject form database
    List<Subject> getAllSubjects();
    // save subject to data base
    void saveSubject(Subject subject);
    // for find total subject in database
    int getTotalSubjects();
    // for Remove subject 
    void deletedSubjectByNo(long no);
    Subject getSubjectById(Long id);
} 