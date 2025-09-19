package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.ObjectNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Факультет с id " + id + " не найден"));
    }

    public Student editStudent(Student student) {
        studentRepository.findById(student.getId()).orElseThrow(() -> new ObjectNotFoundException("Факультет с id " + student.getId() + " не найден"));
        return studentRepository.save(student);
    }

    public void removeStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> findAllStudentsByAge(Integer age) {
        return studentRepository.findByAge(age);
    }

    public List<Student> findStudentsByAgeBetween(Integer min, Integer max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();

    }

    public Faculty findFacultyByStudent(Long id) {
        return studentRepository.findById(id).get().getFaculty();
    }

    public int getStudentAmount() {
        return studentRepository.getStudentAmount();
    }

    public int getAverageAge() {
        return studentRepository.getAverageAge();
    }

    public List<Student> getLastFive() {
        return studentRepository.getLastFive();
    }

}


