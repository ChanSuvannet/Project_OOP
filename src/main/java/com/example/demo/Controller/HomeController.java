package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
     @GetMapping("/register")
    public String Register() {
        return "register";
    }

    @PostMapping("/login")
    public String login(@RequestParam("loginName") String id,
                        @RequestParam("loginPassword") String password,
                        Model model) {

        boolean isValidUser = isUserValid(id, password);

        if (isValidUser) {
            return "redirect:/data";
        } else {
            model.addAttribute("error", "Invalid credentials. Please try again.");
            return "login"; 
        }
    }
    private boolean isUserValid(String id, String password) {
        return id.equals("e20210429") && password.equals("12345678");
    }
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
        // find all subject 
        int getTotalSubjects = subjectService.getTotalSubjects();
        model.addAttribute("totalSubjects", getTotalSubjects);
        // * for Student
        // show subject form sql
        model.addAttribute("students", studentService.getAllStudents());
        // add new student
        model.addAttribute("newStudent", new Student());
        int TotalStudents = studentService.TotalStudents();
        model.addAttribute("totalStudents", TotalStudents);
         // Total male and female Student
        int TotalMaleStudents = studentService.TotalMaleStudents();
        int TotalFemaleStudents = studentService.TotalFemaleStudents();
        model.addAttribute("totalMaleStudents", TotalMaleStudents);
        model.addAttribute("totalFemaleStudents", TotalFemaleStudents);
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

