package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Teacher;
import com.example.demo.service.TeacherService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller
public class HomeController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/data")
    public String viewHomePage(Model model) {

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
        System.out.println("Loading data page"); // Just for logging, if needed
        return "data";
    }

    // add new teacher
    @PostMapping("/saveTeacher")
    public String saveTeacher(@ModelAttribute("newTeacher") Teacher teacher){
        teacherService.saveTeacher(teacher);
        return "redirect:/data"; // Redirect to the data page after saving
    }
    // deleted teacher
    @GetMapping("/deleteTeacher/{number}")
	public String deleteEmployee(@PathVariable (value = "number") long number) {
		this.teacherService.deletedTeacherByNumber(number);
		return "redirect:/data";
	}
    // for update teacher
    @GetMapping("/showFormForUpdate/{number}")
	public String showFormForUpdate(@PathVariable ( value = "number") long number, Model model) {
		Teacher teacher = teacherService.getEmployeeByNumber(number);
		model.addAttribute("teacher", teacher);
		return "update";
	}
}

