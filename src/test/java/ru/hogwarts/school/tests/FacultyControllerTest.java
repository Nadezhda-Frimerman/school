package ru.hogwarts.school.tests;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;

import static org.springframework.http.HttpMethod.PUT;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Transactional
public class FacultyControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void contextLoads() {
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    void findAllFacultiesTest() throws Exception {
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/faculty/findAll", String.class)).isNotNull();
    }

    @Test
    void findAllStudentsByIdTest() throws Exception {
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/faculty/1/findAllStudentsById", String.class)).isNotNull();
    }

    @Test
    void testAddFacultyTest() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Seven");
        faculty.setColor("77");
        Faculty facultyIsAdded = this.testRestTemplate.postForObject("http://localhost:" + port + "/faculty/add", faculty, Faculty.class);
        Assertions.assertThat(facultyIsAdded).isNotNull();
        this.testRestTemplate.exchange(
                "http://localhost:" + port + "/faculty/" + facultyIsAdded.getId() + "/remove",
                HttpMethod.DELETE,
                HttpEntity.EMPTY, void.class);
    }

    @Test
    void updateFacultyTest() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Nine");
        faculty.setColor("99");
        Faculty facultyIsAdded = this.testRestTemplate.postForObject("http://localhost:" + port + "/faculty/add", faculty, Faculty.class);
        Faculty facultyNew = new Faculty();
        facultyNew.setId(facultyIsAdded.getId());
        facultyNew.setName("N");
        facultyNew.setColor("999");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Faculty> requestEntity = new HttpEntity<>(facultyNew, headers);

        Assertions.assertThat(this.testRestTemplate.exchange("http://localhost:" + port + "/faculty/update", PUT, requestEntity, Faculty.class).getBody()).
                isEqualTo(facultyNew);

        this.testRestTemplate.exchange(
                "http://localhost:" + port + "/faculty/" + facultyNew.getId() + "/remove",
                HttpMethod.DELETE,
                HttpEntity.EMPTY, void.class);
    }

    @Test
    void removeFacultyByIdTest() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Seven");
        faculty.setColor("77");
        Faculty facultyIsAdded = this.testRestTemplate.postForObject("http://localhost:" + port + "/faculty/add", faculty, Faculty.class);
        Assertions.assertThat(this.testRestTemplate.exchange(
                "http://localhost:" + port + "/faculty/" + facultyIsAdded.getId() + "/remove",
                HttpMethod.DELETE,
                HttpEntity.EMPTY, void.class).getStatusCode().equals(200));
    }

    @Test
    void findFacultyIdIsNotNullTest() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Ten");
        faculty.setColor("1010");
        Faculty facultyIsAdded = this.testRestTemplate.postForObject("http://localhost:" + port + "/faculty/add", faculty, Faculty.class);
        faculty.setId(facultyIsAdded.getId());
        Assertions.assertThat(this.testRestTemplate.getForObject(
                        "http://localhost:" + port + "/faculty/find?id="+facultyIsAdded.getId(), Faculty.class)
                .equals(faculty));
        this.testRestTemplate.exchange(
                "http://localhost:" + port + "/faculty/"+facultyIsAdded.getId()+"/remove",
                HttpMethod.DELETE,
                HttpEntity.EMPTY, void.class);
    }

    @Test
    void findFacultyIdIsNotNullNegativeTest() throws Exception {
        Assertions.assertThat(this.testRestTemplate.getForObject(
                        "http://localhost:" + port + "/faculty/find?id=1000000000", String.class)
                .isEmpty());
    }

    @Test
    void findFacultyNameIsNotNullTest() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Ten");
        faculty.setColor("1010");
        Faculty facultyIsAdded = this.testRestTemplate.postForObject("http://localhost:" + port + "/faculty/add", faculty, Faculty.class);
        Assertions.assertThat(this.testRestTemplate.getForObject(
                        "http://localhost:" + port + "/faculty/find?name=Ten", Faculty.class)
                .equals(faculty));
        this.testRestTemplate.exchange(
                "http://localhost:" + port + "/faculty/"+facultyIsAdded.getId()+"/remove",
                HttpMethod.DELETE,
                HttpEntity.EMPTY, void.class);
    }

    @Test
    void findFacultyColorIsNotNullTest() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Ten");
        faculty.setColor("1010");
        Faculty facultyIsAdded = this.testRestTemplate.postForObject("http://localhost:" + port + "/faculty/add", faculty, Faculty.class);
        Assertions.assertThat(this.testRestTemplate.getForObject(
                        "http://localhost:" + port + "/faculty/find?color=1010", Faculty.class)
                .equals(faculty));
        this.testRestTemplate.exchange(
                "http://localhost:" + port + "/faculty/"+facultyIsAdded.getId()+"/remove",
                HttpMethod.DELETE,
                HttpEntity.EMPTY, void.class);
    }
}
