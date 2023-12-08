package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Teacher;
import com.example.demo.repository.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    private TeacherRepository teacherRepository;

    
    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }
    @Override
    public void saveTeacher(Teacher teacher){
        this.teacherRepository.save(teacher);
    }
    
    @Override
	public void deletedTeacherByNumber(long number) {
		this.teacherRepository.deleteById(number);
	}
	
    @Override
	public Teacher getEmployeeByNumber(long number) {
		Optional<Teacher> optional = teacherRepository.findById(number);
		Teacher teacher = null;
		if (optional.isPresent()) {
			teacher = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + number);
		}
		return teacher;
	}
    // total teacher
	@Override
    public int getTotalTeachers() {
        return teacherRepository.findAll().size(); // Assuming TeacherRepository extends JpaRepository
    }
    // Method to get total male teachers
    @Override
    public int getTotalMaleTeachers() {
        List<Teacher> maleTeachers = teacherRepository.findByGender("Male");
        return maleTeachers.size();
    }

    // Method to get total female teachers
    @Override
    public int getTotalFemaleTeachers() {
        List<Teacher> femaleTeachers = teacherRepository.findByGender("Female");
        return femaleTeachers.size();
    }
}
