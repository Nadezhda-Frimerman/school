package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    @Query(value="select*from faculty where faculty.id=:id", nativeQuery = true)
    Faculty findFacultyById (Long id);
    Faculty findByNameIgnoreCaseOrColorIgnoreCase(String name,String color);
   
    @Query(value="select*from student where student.faculty_id=:id", nativeQuery = true)
    List<Student> findAllStudentsById(Long id);
}
