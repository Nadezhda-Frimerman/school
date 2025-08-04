package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Faculty findByColorIgnoreCase(String color);
    Faculty findByNameIgnoreCase(String name);
    ;
    @Query(value="select*from student where student.faculty_id=:facultyId", nativeQuery = true)
    List<Student> findAllStudentsByFacultyId(Long facultyId);
}
