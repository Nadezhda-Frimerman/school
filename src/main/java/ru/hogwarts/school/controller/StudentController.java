package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}/find")
    public ResponseEntity<Student> findStudentById(@RequestParam("id") Long studentId) {
        return ResponseEntity.ok(studentService.findStudentById(studentId));
    }

    @GetMapping("/find/all")
    public List<Student> findAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("/find/allByAge")
    public ResponseEntity<List<Student>> findAllStudentsByAge(@RequestParam("age") Integer age) {
        return ResponseEntity.ok(studentService.findAllStudentsByAge(age));
    }

    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.addStudent(student));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.editStudent(student));
    }

    @DeleteMapping("/{id}/remove")
    public ResponseEntity<Student> removeStudentById(@RequestParam("id") Long studentId) {
        studentService.removeStudentById(studentId);
        return ResponseEntity.ok().build();
    }

}
