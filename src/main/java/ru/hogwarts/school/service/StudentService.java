package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class StudentService {
    private static Long studentId = 0L;
    private final Map<Long, Student> students=new HashMap<>();

    public Student addStudent(Student student){
        student.setId(studentId++);
        return students.put(student.getId(),student);
    }

    public Student findStudentById(Long studentId){
        return students.get(studentId);
    }

    public Student removeStudentById(Long studentId){
        return students.remove(studentId);
    }


    public Student editStudent(Long studentId, Student student){
        student.setId(studentId);
        return students.put(studentId,student);
    }

    public List<Student> findAllStudentsByAge (int age){
        return students.values().stream()
                .filter(student -> student.getAge()==age)
                .toList();
    }

    public List<Student> findAllStudents(){
        return students.values().stream()
                .toList();

    }
}
