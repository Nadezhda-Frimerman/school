package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/{id}/find")
    public Faculty findFacultyById(@RequestParam("id") Long facultyId) {
        return facultyService.findFacultyById(facultyId);
    }

    @GetMapping("/find/FacultiesByColor")
    public List<Faculty> findAllFacultiesByColor(@RequestParam("color") String color) {
        return facultyService.findAllFacultiesByColor(color);
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
    public ResponseEntity removeFacultyById(@PathVariable("id") Long facultyId) {
        facultyService.removeFacultyById(facultyId);
        return ResponseEntity.ok().build();
    }
}
