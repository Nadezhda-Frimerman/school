package ru.hogwarts.school.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.ObjectNotFoundException;
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

    public Student findStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new ObjectNotFoundException("Факультет с id " + studentId + " не найден"));
    }

    public Student editStudent(Student student) {
        studentRepository.findById(student.getId()).orElseThrow(() -> new ObjectNotFoundException("Факультет с id " + student.getId() + " не найден"));
        return studentRepository.save(student);
    }

    public void removeStudentById(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public List<Student> findAllStudentsByAge(Integer age) {
        return studentRepository.findByAge(age);
    }

    public List<Student> findStudentsByAgeBetween(Integer min, Integer max){
        return studentRepository.findByAgeBetween(min, max);
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();

    }


}


