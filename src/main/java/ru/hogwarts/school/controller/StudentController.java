package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}/find")
    public Student findStudentById(@PathVariable("id") Long studentId) {
        return studentService.findStudentById(studentId);
    }

    @GetMapping("/find/all")
    public List<Student> findAllStudents() {
        return studentService.findAllStudents();
    }
    @GetMapping("/find/allByAge")
    public List<Student> findAllStudentsByAge(@RequestParam("age") int age) {
        return studentService.findAllStudentsByAge(age);
    }

    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("/{id}/update")
    public Student updateStudent(@PathVariable("id") Long studentId,
                                 @RequestBody Student student) {
        return studentService.editStudent(studentId, student);
    }
    @DeleteMapping("/{id}/remove")
    public Student removeStudentById(@PathVariable("id") Long studentId) {
        return studentService.removeStudentById(studentId);
    }

}
