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

    Faculty findFacultyByColor(String color);

    List<Faculty> findAllFaculties();
    Faculty findFacultyByName (String name);
}
