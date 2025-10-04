package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.ObjectNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import javax.swing.text.StringContent;
import java.util.List;

@Service

public class FacultyServiceImpl implements FacultyService {
    private static final Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);
    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }

    public Faculty findFacultyById(Long id) {
        logger.info("Was invoked method for find faculty by id");
        return facultyRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Факультет с id " + id + " не найден"));
    }

    public void removeFacultyById(Long id) {
        logger.info("Was invoked method for remove faculty");
        facultyRepository.deleteById(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("Was invoked method for edit faculty");
        facultyRepository.findById(faculty.getId()).orElseThrow(() -> new ObjectNotFoundException("Факультет с id " + faculty.getId() + " не найден"));
        return facultyRepository.save(faculty);
    }


    public List<Faculty> findAllFaculties() {
        logger.info("Was invoked method for find all faculties");
        return facultyRepository.findAll();
    }

    public Faculty findFaculty (Long id, String name, String color){
        if (id != null && id != 0L) {
            return facultyRepository.findFacultyById(id);
        }
        if ((name != null && !name.isBlank()) || (color != null && !color.isBlank())) {
            return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(name, color);
        }
        logger.error("Faculty is not found");
        throw new ObjectNotFoundException("Факультет не найден");
    }

    public List<Student> findAllStudentsById(Long id){
        logger.info("Was invoked method for find all students by faculty id");
        return facultyRepository.findAllStudentsById(id);
    }
}
