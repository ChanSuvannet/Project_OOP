package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.demo.model.Subject;
import com.example.demo.repository.SubjectRepository;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<Subject> getAllSubjects(){
        return subjectRepository.findAll();
    }
    @Override
    // add new subject
    public void saveSubject(Subject subject) {
        subjectRepository.save(subject);
    }
    // function for find all subject size
    @Override
    public int getTotalSubjects(){
        return subjectRepository.findAll().size();
    }

    // remove subject
    
    @Override
	public void deletedSubjectByNo(long no) {
		this.subjectRepository.deleteById(no);
	}
}