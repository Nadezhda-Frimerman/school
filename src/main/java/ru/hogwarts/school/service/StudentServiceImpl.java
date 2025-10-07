package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.ObjectNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    Logger logger = LoggerFactory.getLogger(Student.class);
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public Student findStudentById(Long id) {
        logger.info("Was invoked method for find student by id");
        return studentRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Факультет с id " + id + " не найден"));
    }

    public Student editStudent(Student student) {
        logger.info("Was invoked method for edit student");
        studentRepository.findById(student.getId()).orElseThrow(() -> new ObjectNotFoundException("Факультет с id " + student.getId() + " не найден"));
        return studentRepository.save(student);
    }

    public void removeStudentById(Long id) {
        logger.info("Was invoked method for remove student");
        studentRepository.deleteById(id);
    }

    public List<Student> findAllStudentsByAge(Integer age) {
        logger.info("Was invoked method for find all students by age");
        return studentRepository.findByAge(age);
    }

    public List<Student> findStudentsByAgeBetween(Integer min, Integer max) {
        logger.info("Was invoked method for find all students by age between");
        return studentRepository.findByAgeBetween(min, max);
    }

    public List<Student> findAllStudents() {
        logger.info("Was invoked method for find all students");
        return studentRepository.findAll();

    }

    public Faculty findFacultyByStudent(Long id) {
        logger.info("Was invoked method for find faculty by student");
        return studentRepository.findById(id).get().getFaculty();
    }

    public int getStudentAmount() {
        logger.info("Was invoked method for get student amount");
        return studentRepository.getStudentAmount();
    }

    public int getAverageAge() {
        logger.info("Was invoked method for get average age of students");
        return studentRepository.getAverageAge();
    }

    public List<Student> getLastFive() {
        logger.info("Was invoked method for get five last students");
        return studentRepository.getLastFive();
    }
    public List<String>  getAllStudentsByFirstLetterOfName (){
       return studentRepository.findAll().
               stream().
               parallel().
               map(Student:: getName).
               filter(n->n.toUpperCase().startsWith("A")).
               sorted().
               toList();
    }
    public double getAverageAgeStream(){
        return studentRepository.findAll().
                stream().mapToDouble(Student:: getAge).average().orElseThrow();
    }
}


