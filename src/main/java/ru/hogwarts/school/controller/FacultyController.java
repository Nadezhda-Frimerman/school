package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.exception.ObjectNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/findAll")
    public List<Faculty> findAllFaculties() {
        return facultyService.findAllFaculties();
    }

    @GetMapping("/{id}/findAllStudentsByid")
    public List<Student> findAllStudentsByid(@PathVariable("id") Long id) {
        return facultyService.findAllStudentsByid(id);
    }

    @PostMapping("/add")
    public Faculty addFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @PutMapping("/update")
    public Faculty updateFaculty(@RequestBody Faculty faculty) {

        return facultyService.editFaculty(faculty);
    }

    @DeleteMapping("/{id}/remove")
    public ResponseEntity removeFacultyById(@PathVariable("id") Long id) {
        facultyService.removeFacultyById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find")
    public ResponseEntity findFaculty(@RequestParam("id") Long id, @RequestParam("name") String name, @RequestParam("color") String color) {
         return ResponseEntity.ok(facultyService.findFaculty(id, name, color));


    }

}
