package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Student;
import com.example.demo.model.Subject;
import com.example.demo.model.Teacher;
import com.example.demo.service.StudentService;
import com.example.demo.service.SubjectService;
import com.example.demo.service.TeacherService;

@Controller
public class HomeController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private StudentService studentService;
    @GetMapping("/data")
    public String viewHomePage(Model model) {

        // for teacher
        // show teacher form sql
        model.addAttribute("teachers", teacherService.getAllTeachers());
        // add new teacher
        model.addAttribute("newTeacher", new Teacher());
        // total teacher
        // Retrieve total number of teachers
        int totalTeachers = teacherService.getTotalTeachers();
        model.addAttribute("totalTeachers", totalTeachers);
        // Total male and female teachers
        int totalMaleTeachers = teacherService.getTotalMaleTeachers();
        int totalFemaleTeachers = teacherService.getTotalFemaleTeachers();
        model.addAttribute("totalMaleTeachers", totalMaleTeachers);
        model.addAttribute("totalFemaleTeachers", totalFemaleTeachers);
        // * for subject
        // show subject form sql
        model.addAttribute("subjects", subjectService.getAllSubjects());
        // add new subject
        model.addAttribute("newSubject", new Subject());
        // * for subject
        // show subject form sql
        model.addAttribute("students", studentService.getAllStudents());
        // add new subject
        model.addAttribute("newStudent", new Student());
        System.out.println("Loading data page");
        return "data";
    }

    // for update teacher
    @GetMapping("/showFormForUpdate/{number}")
	public String showFormForUpdate(@PathVariable ( value = "number") long number, Model model) {
		Teacher teacher = teacherService.getTeacherByNumber(number);
		model.addAttribute("teacher", teacher);
		return "update";
	}
    // add new teacher
    @PostMapping("/saveTeacher")
    public String saveTeacher(@ModelAttribute("newTeacher") Teacher teacher){
        teacherService.saveTeacher(teacher);
        return "redirect:/data"; // Redirect to the data page after saving
    }
    // add new subject
    @PostMapping("/saveSubject")
    public String saveSubject(@ModelAttribute("newSubject") Subject subject){
        subjectService.saveSubject(subject);
        return "redirect:/data"; // Redirect to the data page after saving
    }
     // add new subject
    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("newStudent") Student student){
        studentService.saveStudent(student);
        return "redirect:/data"; // Redirect to the data page after saving
    }
    
    // deleted teacher
    @GetMapping("/deleteTeacher/{number}")
	public String deleteEmployee(@PathVariable (value = "number") long number) {
		this.teacherService.deletedTeacherByNumber(number);
		return "redirect:/data";
	}
}

