package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hogwarts.school.exception.ObjectNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFacultyById(Long facultyId) {
        return facultyRepository.findById(facultyId).orElseThrow(() -> new ObjectNotFoundException("Факультет с id " + facultyId + " не найден"));
    }

    public void removeFacultyById(Long facultyId) {
        facultyRepository.deleteById(facultyId);
    }

    public Faculty editFaculty(Faculty faculty) {
        facultyRepository.findById(faculty.getId()).orElseThrow(() -> new ObjectNotFoundException("Факультет с id " + faculty.getId() + " не найден"));
        return facultyRepository.save(faculty);
    }

    public List<Faculty> findAllFacultiesByColor(String color) {
        return facultyRepository.findByColor(color);
    }

    public List<Faculty> findAllFaculties() {
        return facultyRepository.findAll();
    }


}
