package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import ru.hogwarts.school.model.Faculty;
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
    public ResponseEntity<Student> findStudentById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(studentService.findStudentById(id));
    }

    @GetMapping("/find/all")
    public List<Student> findAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("/find/allByAge")
    public ResponseEntity<List<Student>> findAllStudentsByAge(@RequestParam("age") Integer age) {
        return ResponseEntity.ok(studentService.findAllStudentsByAge(age));
    }
    @GetMapping("/find/allByAgeBetween")
    public ResponseEntity<List<Student>> findStudentsByAgeBetween(@RequestParam("min") Integer min,@RequestParam("max") Integer max) {
        return ResponseEntity.ok(studentService.findStudentsByAgeBetween(min,max));
    }
    @GetMapping("/{id}/findFacultyBy Student")
    public ResponseEntity <Faculty> findFacultyByStudent(@RequestParam("id") Long id){
        return ResponseEntity.ok(studentService.findFacultyByStudent(id));
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
    public ResponseEntity<Student> removeStudentById(@RequestParam("id") Long id) {
        studentService.removeStudentById(id);
        return ResponseEntity.ok().build();
    }

}
