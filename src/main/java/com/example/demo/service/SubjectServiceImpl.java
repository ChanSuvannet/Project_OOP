package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.example.demo.model.Subject;
import com.example.demo.model.Teacher;
import com.example.demo.repository.SubjectRepository;

@Service
public class SubjectServiceImpl implements SubjectService {
    

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<Subject> getAllSubjects(){
        return subjectRepository.findAll();
    }
    // add new teacher
    public void saveSubject(Subject subject) {
        subjectRepository.save(subject);
    }
}
