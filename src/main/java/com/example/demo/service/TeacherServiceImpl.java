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

    // read akk teacher from database
    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    // add new teacher
    public void saveTeacher(Teacher teacher) {
        String idToCheck = teacher.getId();
        Optional<Teacher> existingTeacher = teacherRepository.findById(idToCheck);

        if (existingTeacher.isEmpty()) {
            // The teacher with the same ID doesn't exist, so save the new teacher
            teacherRepository.save(teacher);
        } else {
            // Perform any specific action if the ID already exists, such as updating or ignoring
            // Here, we are ignoring the duplicate entry for simplicity
            System.out.println("Teacher with ID " + idToCheck + " already exists in the database.");
        }
    }
    
    // deleted teacher
    @Override
	public void deletedTeacherByNumber(long number) {
		this.teacherRepository.deleteById(number);
	}
	
    // update info teacher
    @Override
	public Teacher getTeacherByNumber(long number) {
		Optional<Teacher> optional = teacherRepository.findById(number);
		Teacher teacher = null;
		if (optional.isPresent()) {
			teacher = optional.get();
		} else {
			throw new RuntimeException(" Teacher not found for id :: " + number);
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
