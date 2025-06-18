package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/{id}/find")
    public Faculty findFacultyById(@PathVariable("id") Long facultyId) {
        return facultyService.findFacultyById(facultyId);
    }

    @GetMapping("/find/FacultiesByColor")
    public List<Faculty> findAllFacultiesByColor(@RequestParam("color") String color) {
        return facultyService.findAllFacultysByColor(color);
    }

    @PostMapping("/add")
    public Faculty addFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @PutMapping("/{id}/update")
    public Faculty updateFaculty(@PathVariable("id") Long facultyId,
                                 @RequestBody Faculty faculty) {
        return facultyService.editFaculty(facultyId,faculty);
    }
    @DeleteMapping("/{id}/remove")
    public Faculty removeFacultyById(@PathVariable("id") Long facultyId) {
        return facultyService.removeFacultyById(facultyId);
    }
}
