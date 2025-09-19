package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;

@Service
public interface StudentService {
    Student addStudent(Student student);

    Student findStudentById(Long id);

    Student editStudent(Student student);

    void removeStudentById(Long id);

    List<Student> findAllStudentsByAge(Integer age);

    List<Student> findStudentsByAgeBetween(Integer min, Integer max);

    List<Student> findAllStudents();

    Faculty findFacultyByStudent(Long id);

    int getStudentAmount();

    int getAverageAge();

    List<Student> getLastFive();
}
