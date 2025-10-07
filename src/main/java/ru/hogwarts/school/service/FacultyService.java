package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;
import java.util.Optional;

@Service
public interface FacultyService {
    Faculty addFaculty(Faculty faculty);

    Faculty findFacultyById(Long id);

    void removeFacultyById(Long id);

    Faculty editFaculty(Faculty faculty);


    List<Faculty> findAllFaculties();
    Faculty findFaculty (Long id, String name, String color);
    List<Student> findAllStudentsById(Long id);
    Optional<Faculty> maxLogNameFaculty();
    long count();
}
