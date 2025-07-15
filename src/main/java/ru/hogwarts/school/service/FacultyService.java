package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.List;
@Service
public interface FacultyService {
    Faculty addFaculty(Faculty faculty);

    Faculty findFacultyById(Long facultyId);

    void removeFacultyById(Long facultyId);

    Faculty editFaculty(Faculty faculty);

    List<Faculty> findAllFacultiesByColor(String color);

    List<Faculty> findAllFaculties(String color);
}
