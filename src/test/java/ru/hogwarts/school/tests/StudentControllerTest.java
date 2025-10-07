package ru.hogwarts.school.tests;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Transactional
public class StudentControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void contextLoads () {
        Assertions.assertThat(studentController).isNotNull();
    }
    @Test
    void findStudentByIdTest() throws Exception {
        Student student = new Student();
        student.setName("Dolly");
        student.setAge(21);
        Student studentIsAdded=this.testRestTemplate.postForObject("http://localhost:" + port + "/student/add", student, Student.class);
        Assertions.assertThat(this.testRestTemplate.getForObject(
                        "http://localhost:" + port + "/faculty/find?id="+studentIsAdded.getId(), Student.class)
                .equals(student));
        this.testRestTemplate.exchange(
                "http://localhost:" + port + "/faculty/"+studentIsAdded.getId()+"/remove",
                HttpMethod.DELETE,
                HttpEntity.EMPTY, void.class);
    }

    @Test
    void findAllStudentsTest() throws Exception {
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:"+port+"/student/find/all", String.class)).isNotNull();
    }

    @Test
    void findAllStudentsByAgeTest() throws Exception {
        Student student = new Student();
        student.setName("Dolly");
        student.setAge(21);
        Student studentIsAdded=this.testRestTemplate.postForObject(
                "http://localhost:" + port + "/student/add", student, Student.class);
        Assertions.assertThat(this.testRestTemplate.getForObject(
                "http://localhost:"+port+"/student/findAllStudentsByAge?age=21",
                String.class))
                .isNotNull();
        this.testRestTemplate.exchange(
                "http://localhost:" + port + "/student/"+studentIsAdded.getId()+"/remove",
                HttpMethod.DELETE,
                HttpEntity.EMPTY, void.class);
    }

    @Test
    void findStudentsByAgeBetweenTest() throws Exception {
        Student student = new Student();
        student.setName("Dolly");
        student.setAge(21);
        Student studentIsAdded=this.testRestTemplate.postForObject(
                "http://localhost:" + port + "/student/add", student, Student.class);
        Assertions.assertThat(this.testRestTemplate.getForObject(
                "http://localhost:"+port+"/student/findAllStudentsByAgeBetween?age=20&?age=21",
                String.class))
                .isNotNull();
        this.testRestTemplate.exchange(
                "http://localhost:" + port + "/student/"+studentIsAdded.getId()+"/remove",
                HttpMethod.DELETE,
                HttpEntity.EMPTY, void.class);
    }

    @Test
    void findFacultyByStudentTest() throws Exception {
        Faculty faculty1 = new Faculty("One", "111", 1L);
        Student student = new Student();
        student.setName("Dolly");
        student.setAge(21);
        student.setFaculty(faculty1);
        Student studentIsAdded=this.testRestTemplate.postForObject(
                "http://localhost:" + port + "/student/add", student, Student.class);
        student.setId(studentIsAdded.getId());
        Assertions.assertThat(this.testRestTemplate.getForObject(
                "http://localhost:"+port+"/student/"+studentIsAdded.getId()+"/findFacultyByStudent",
                Faculty.class)
                .equals(student.getFaculty()));
        this.testRestTemplate.exchange(
                "http://localhost:" + port + "/student/"+studentIsAdded.getId()+"/remove",
                HttpMethod.DELETE,
                HttpEntity.EMPTY, void.class);
    }

    @Test
    void addStudentTest() throws Exception {
        Student student = new Student();
        student.setName("Dolly");
        student.setAge(21);
        Student studentIsAdded=this.testRestTemplate.postForObject(
                "http://localhost:" + port + "/student/add", student, Student.class);
        student.setId(studentIsAdded.getId());
        Assertions.assertThat(studentIsAdded.equals(student));
        this.testRestTemplate.exchange(
                "http://localhost:" + port + "/student/"+studentIsAdded.getId()+"/remove",
                HttpMethod.DELETE,
                HttpEntity.EMPTY, void.class);
    }

    @Test
    void updateStudentTest() throws Exception {
        Student student = new Student();
        student.setName("Dolly");
        student.setAge(21);
        Student studentIsAdded=this.testRestTemplate.postForObject(
                "http://localhost:" + port + "/student/add", student, Student.class);
        student.setId(studentIsAdded.getId());
        student.setAge(19);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Student> requestEntity = new HttpEntity<>(student, headers);
        Assertions.assertThat(this.testRestTemplate.exchange("http://localhost:" + port + "/student/update",
                HttpMethod.PUT,
                requestEntity,
                Student.class)
                .equals(studentIsAdded));
        this.testRestTemplate.exchange(
                "http://localhost:" + port + "/student/"+studentIsAdded.getId()+"/remove",
                HttpMethod.DELETE,
                HttpEntity.EMPTY, void.class);
    }

    @Test
    void removeStudentByIdTest() throws Exception {
        Student student = new Student();
        student.setName("Dolly");
        student.setAge(21);
        Student studentIsAdded=this.testRestTemplate.postForObject(
                "http://localhost:" + port + "/student/add", student, Student.class);
        Assertions.assertThat(this.testRestTemplate.exchange(
                "http://localhost:" + port + "/student/" + studentIsAdded.getId() + "/remove",
                HttpMethod.DELETE,
                HttpEntity.EMPTY, void.class).getStatusCode().equals(200));
    }
    @Test
    void getAllStudentsNamesParallel() throws Exception{
        Assertions.assertThat(this.testRestTemplate.exchange(
                "http://localhost:" + port + "/print-parallel",
                HttpMethod.GET,
                HttpEntity.EMPTY, void.class).getStatusCode().equals(200));
    }
    @Test
    void getAllStudentsNamesSynchronized() throws Exception{
        Assertions.assertThat(this.testRestTemplate.exchange(
                "http://localhost:" + port + "/print-synchronized",
                HttpMethod.GET,
                HttpEntity.EMPTY, void.class).getStatusCode().equals(200));
    }
}
