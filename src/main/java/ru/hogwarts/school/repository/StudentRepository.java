package ru.hogwarts.school.repository;


import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAge(Integer age);

    List<Student> findByAgeBetween(Integer min, Integer max);

    @Query(value = "SELECT COUNT (*) FROM student", nativeQuery = true)
    int getStudentAmount();

    @Query(value = "SELECT AVG (AGE) FROM student AS averageAge", nativeQuery = true)
    int getAverageAge();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Student> getLastFive();
}

