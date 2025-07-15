package ru.hogwarts.school.service;

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

        System.out.println(student);
        return studentRepository.save(student);
    }

    public Student findStudentById(Long studentId) {
        CheckStudentExist(studentId);
        return studentRepository.findById(studentId).orElseThrow();
    }

    public Student editStudent(Student student) {
        CheckStudentExist(student.getId());
        return studentRepository.save(student);
    }

    public void removeStudentById(Long studentId) {
        CheckStudentExist(studentId);
        studentRepository.deleteById(studentId);
    }

    public List<Student> findAllStudentsByAge(Integer age) {
        return studentRepository.findByAge(age);
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();

    }

    private void CheckStudentExist(Long studentID) {
        if (studentRepository.getById(studentID)==null) {
            throw new ObjectNotFoundException("Студент с таким Id не найден");
        }
    }
}


