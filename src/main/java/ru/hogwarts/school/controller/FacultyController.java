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
    @GetMapping("/{id}/findAllStudentsByFacultyId")
    public List<Student> findAllStudentsByFacultyId(@PathVariable("id") Long facultyId){
        return facultyService.findAllStudentsByFacultyId(facultyId);
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
    @GetMapping("/find")
    public ResponseEntity findFaculty(@RequestParam("id") Long facultyId, @RequestParam("name") String name,@RequestParam("color") String color) {
        if (facultyId!=null&&facultyId!=0L){
            return ResponseEntity.ok(facultyService.findFacultyById(facultyId));
        }
        if (name!=null&&!name.isBlank()){
            return ResponseEntity.ok(facultyService.findFacultyByName(name));
        }
        if (color!=null&&!color.isBlank()){
            return ResponseEntity.ok(facultyService.findFacultyByColor(color));
        }
        throw new ObjectNotFoundException("Факультет не найден");
    }

}
