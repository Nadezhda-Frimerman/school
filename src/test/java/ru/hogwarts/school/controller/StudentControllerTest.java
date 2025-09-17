package ru.hogwarts.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private StudentRepository studentRepository;
    @MockitoSpyBean
    private StudentService studentService;
    @InjectMocks
    private StudentController studentController;

    @Test
    public void findIdOnlyTest() throws Exception {
        Student student = new Student("Dolly", 20, 1L);
        student.setId(1L);

        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/find")
                        .param("id", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1.0))
                .andExpect(jsonPath("$.name").value("Dolly"))
                .andExpect(jsonPath("$.age").value(20));
    }

    @Test
    void findAllStudentsTest() throws Exception {
        Student student1 = new Student("Tom", 20, 1L);
        Student student2 = new Student("Ann", 21, 2L);
        Student student3 = new Student("Nick", 22, 3L);
        List<Student> students = new ArrayList<>(List.of(student1, student2, student3));

        when(studentRepository.findAll()).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/find/all") //send
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$[0].name").value("Tom"))
                .andExpect(jsonPath("$[0].age").value(20))
                .andExpect(jsonPath("$[1].name").value("Ann"))
                .andExpect(jsonPath("$[1].age").value(21))
                .andExpect(jsonPath("$[2].name").value("Nick"))
                .andExpect(jsonPath("$[2].age").value(22));
    }

    @Test
    void findAllStudentsByAgeTest() throws Exception {
        Student student1 = new Student("Ann", 22, 1L);
        Student student2 = new Student("Nick", 22, 2L);

        List<Student> students = new ArrayList<>(List.of(student1, student2));

        when(studentRepository.findByAge(any(int.class))).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/find/allByAge") //send
                        .param("age", "22")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$[0].name").value("Ann"))
                .andExpect(jsonPath("$[0].age").value(22))
                .andExpect(jsonPath("$[1].name").value("Nick"))
                .andExpect(jsonPath("$[1].age").value(22));
    }

    @Test
    void findStudentsByAgeBetweenTest() throws Exception {
        Student student1 = new Student("Ann", 21, 1L);
        Student student2 = new Student("Nick", 22, 2L);

        List<Student> students = new ArrayList<>(List.of(student1, student2));

        when(studentRepository.findByAgeBetween(any(int.class), any(int.class))).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/find/allByAgeBetween") //send
                        .param("min", "20")
                        .param("max", "22")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$[0].name").value("Ann"))
                .andExpect(jsonPath("$[0].age").value(21))
                .andExpect(jsonPath("$[1].name").value("Nick"))
                .andExpect(jsonPath("$[1].age").value(22));
    }

    @Test
    void findFacultyByStudentTest() throws Exception {
        Student student = new Student("Dolly", 20, 1L);
        student.setId(1L);
        Faculty faculty = new Faculty("One", "111", 1L);
        faculty.setId(1L);
        student.setFaculty(faculty);

        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/findFacultyByStudent")
                        .param("id", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1.0))
                .andExpect(jsonPath("$.name").value("One"))
                .andExpect(jsonPath("$.color").value("111"));
    }

    @Test
    void addStudentTest() throws Exception {
        Student student = new Student("Dolly", 20, 1L);
        student.setId(1L);
        JSONObject studentObject = new JSONObject();
        studentObject.put("id", 1.0);
        studentObject.put("name", "Dolly");
        studentObject.put("age", 20);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student/add") //send
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(1.0))
                .andExpect(jsonPath("$.name").value("Dolly"))
                .andExpect(jsonPath("$.age").value(20));
    }

    @Test
    void updateStudentTest() throws Exception {
        Student student1 = new Student("Ann", 20, 1L);
        student1.setId(1L);
        Student student5 = new Student("Dolly", 21, 1L);
        student5.setId(1L);


        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student1));
        when(studentRepository.save(any(Student.class))).thenReturn(student5);

        ObjectMapper objectMapper = new ObjectMapper();
        String studentJson = objectMapper.writeValueAsString(student5);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student/update")
                        .content(studentJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.name").value("Dolly"))
                .andExpect(jsonPath("$.age").value(21));
    }

    @Test
    void removeStudentByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/remove") //send
                        .param("id", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); //receive

    }
}