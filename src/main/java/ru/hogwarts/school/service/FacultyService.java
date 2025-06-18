package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FacultyService {
    private Long facultyId = 0L;
    private final Map<Long, Faculty> faculties= new HashMap<>();

    public Faculty addFaculty(Faculty faculty){
        faculty.setId(facultyId++);
        return faculties.put(faculty.getId(),faculty);
    }

    public Faculty findFacultyById(Long facultyId){
        return faculties.get(facultyId);
    }

    public Faculty removeFacultyById(Long facultyId){
        return faculties.remove(facultyId);
    }


    public Faculty editFaculty(Long facultyId, Faculty faculty){
        faculty.setId(facultyId);
        return faculties.put(facultyId,faculty);
    }
    public List<Faculty> findAllFacultysByColor (String color){
        return faculties.values().stream()
                .filter(Faculty->Faculty.getColor().equals(color))
                .toList();
    }
}
