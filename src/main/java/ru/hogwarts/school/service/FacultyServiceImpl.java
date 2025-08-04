package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.ObjectNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.List;

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
        facultyRepository.findById(faculty.getFacultyId()).orElseThrow(() -> new ObjectNotFoundException("Факультет с id " + faculty.getFacultyId() + " не найден"));
        return facultyRepository.save(faculty);
    }

    public Faculty findFacultyByColor(String color) {
        return facultyRepository.findByColorIgnoreCase(color);
    }

    public List<Faculty> findAllFaculties() {
        return facultyRepository.findAll();
    }

    public Faculty findFacultyByName (String name){
        return facultyRepository.findByNameIgnoreCase(name);
    }
    public List<Student> findAllStudentsByFacultyId(Long facultyId){
        return facultyRepository.findAllStudentsByFacultyId(facultyId);
    }
}
