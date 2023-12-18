package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {
    
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Override
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }
    @Override
    public void saveStudent(Student student){
        studentRepository.save(student);
    }
}
