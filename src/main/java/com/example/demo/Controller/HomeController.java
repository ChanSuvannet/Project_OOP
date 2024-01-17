package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.model.Score;
import com.example.demo.model.Student;
import com.example.demo.model.Subject;
import com.example.demo.model.Teacher;
import com.example.demo.model.User;
import com.example.demo.service.ScoreService;
import com.example.demo.service.StudentService;
import com.example.demo.service.SubjectService;
import com.example.demo.service.TeacherService;
import com.example.demo.service.UserService;

@Controller
public class HomeController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;
    @Autowired
    private ScoreService scoreService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String Register(Model model) {
        ModelWithUserData(model);
        return "register";
    }

    @PostMapping("/login")
    public String login(@RequestParam("loginName") String UserId,
            @RequestParam("loginPassword") String password,
            Model model) {
        User user = userService.validateUser(UserId, password);

        if (user != null) {
            model.addAttribute("user", user);
            return "Welcome";
        } else {
            model.addAttribute("error", "Invalid credentials. Please try again.");
            return "loading";
        }
    }

    // Display home page with data
    @GetMapping("/data")
    public String viewHomePage(Model model) {
        // model with data for the view
        ModelWithTeacherData(model);
        ModelWithSubjectData(model);
        ModelWithStudentData(model);
        ModelWithScoreData(model);

        System.out.println("Loading data page");
        return "data";
    }

    private void ModelWithScoreData(Model model) {
        // Add a new Score object to the model
        model.addAttribute("saveScore", new Score());
        model.addAttribute("scores", scoreService.getAllScores());
        model.addAttribute("scoresGroupedByStudent", scoreService.getScoresGroupedByStudent());
        model.addAttribute("totalScore", scoreService.getTotalScoreByStudent());
        model.addAttribute("averageScore", scoreService.getAverageScoreByStudent());
        model.addAttribute("rank", scoreService.getRankByStudent());
        model.addAttribute("gradeByStudent", scoreService.getGradeByStudent());
    }

    // model for user
    private void ModelWithUserData(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("newUser", new User());
    }

    // model with teacher data
    private void ModelWithTeacherData(Model model) {
        model.addAttribute("teachers", teacherService.getAllTeachers());
        model.addAttribute("newTeacher", new Teacher());
        model.addAttribute("totalTeachers", teacherService.getTotalTeachers());
        model.addAttribute("totalMaleTeachers", teacherService.getTotalMaleTeachers());
        model.addAttribute("totalFemaleTeachers", teacherService.getTotalFemaleTeachers());
    }

    // model with subject data
    private void ModelWithSubjectData(Model model) {
        model.addAttribute("subjects", subjectService.getAllSubjects());
        model.addAttribute("newSubject", new Subject());
        model.addAttribute("totalSubjects", subjectService.getTotalSubjects());
    }

    // model with student data
    private void ModelWithStudentData(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("newStudent", new Student());
        model.addAttribute("totalStudents", studentService.TotalStudents());
        model.addAttribute("totalMaleStudents", studentService.TotalMaleStudents());
        model.addAttribute("totalFemaleStudents", studentService.TotalFemaleStudents());
    }

    // Display form for updating teacher
    @GetMapping("/showFormForUpdate/{number}")
    public String showFormForUpdate(@PathVariable(value = "number") long number, Model model) {
        Teacher teacher = teacherService.getTeacherByNumber(number);
        model.addAttribute("teacher", teacher);
        return "update";
    }

    // Display form for updating password User
    @GetMapping("/User/{id}")
    public String showFormForUpdateUser(@PathVariable(value = "id") long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user1", user);
        return "user";
    }

    @GetMapping("/ForgotPassword")
    public String showForgotPasswordForm() {
        return "ForgotPassword";
    }
    @GetMapping("/sspage")
    public String sspage() {
        return "sspage";
    }

    // @PostMapping("/sendMessage")
    // public String sendMessage(@RequestParam String phone) throws
    // InterruptedException {
    // if (phone != null) {
    // System.out.println("Phone number received: " + phone);
    // Thread.sleep(4000);
    // return userService.sendTelegram(phone);
    // }
    // return "No phone number provided.";
    // }
    @PostMapping("/sendEmail")
    public RedirectView sendEmail(@RequestParam String email) throws InterruptedException {

        if (email != null) {
            System.out.println("Have value email .");
            Thread.sleep(1000);
            userService.sendEmailToUser(email);
            return new RedirectView("sspage");
        }
        return null;
    }

    // @PostMapping("/sendEmail")
    // public String sendEmail(@RequestParam("email") String email) throws
    // InterruptedException{
    // // test send
    // try {
    // SimpleMailMessage message = new SimpleMailMessage();
    // message.setFrom("SchoolITC@gmail.com");
    // message.setTo(email);
    // message.setSubject("Password Reset Request");
    // mailSender.send(message);
    // } catch (Exception e) {
    // e.printStackTrace();
    // System.out.println("Cant't Send Message to this Email");
    // }
    // return "redirect:/ForgotPassword";
    // }
    // Display form for updating update score for student
    @GetMapping("/showFormForUpdateScore/{id}")
    public String showFormForUpdateScore(@PathVariable(value = "id") long id, Model model) {
        Score score = scoreService.getScoreByNumber(id);
        model.addAttribute("score", score);
        return "updateScore";
    }

    @PostMapping("/saveScore")
    public String saveScore(@ModelAttribute("newScore") Score score, @RequestParam("student") Long ID,
            @RequestParam("subject") Long no) {
        Student student = studentService.getStudentById(ID);
        Subject subject = subjectService.getSubjectById(no);
        score.setStudent(student);
        score.setSubject(subject);
        scoreService.saveScore(score);
        return "redirect:/data";
    }

    // saveUser
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("newUser") User user) throws InterruptedException {
        userService.saveUser(user);
        Thread.sleep(5000);
        return "redirect:/login";
    }

    // Process new teacher form
    @PostMapping("/saveTeacher")
    public String saveTeacher(@ModelAttribute("newTeacher") Teacher teacher) {
        teacherService.saveTeacher(teacher);
        return "redirect:/data"; // Redirect to the data page after saving
    }

    @PostMapping("/saveSubject")
    public String saveSubject(@ModelAttribute("newSubject") Subject subject) {
        subjectService.saveSubject(subject);

        return "redirect:/data"; // Redirect to the data page after saving
    }

    // Process new student form
    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("newStudent") Student student) {
        studentService.saveStudent(student);
        return "redirect:/data"; // Redirect to the data page after saving
    }

    // Delete teacher
    @GetMapping("/deleteTeacher/{number}")
    public String deleteTeacher(@PathVariable(value = "number") long number) {
        this.teacherService.deletedTeacherByNumber(number);
        return "redirect:/data";
    }

    // Delete subject
    @GetMapping("/deleteSubject/{no}")
    public String deletedSubject(@PathVariable(value = "no") long no) {
        this.subjectService.deletedSubjectByNo(no);
        return "redirect:/data";
    }
}