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
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class FacultyControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private FacultyRepository facultyRepository;
    @MockitoSpyBean
    private FacultyService facultyService;
    @InjectMocks
    private FacultyController facultyController;


    @Test
    public void addFacultyTest() throws Exception {
        Faculty faculty1 = new Faculty("One", "111", 1L);
        faculty1.setId(1L);
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("id", 1.0);
        facultyObject.put("name", "One");
        facultyObject.put("color", "111");

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty1);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty/add") //send
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(1.0))
                .andExpect(jsonPath("$.name").value("One"))
                .andExpect(jsonPath("$.color").value("111"));
    }

    @Test
    public void findAllFacultiesTest() throws Exception {
        Faculty faculty1 = new Faculty("One", "111", 1L);
        Faculty faculty2 = new Faculty("Two", "222", 2L);
        Faculty faculty3 = new Faculty("Three", "333", 3L);
        List<Faculty> faculties = new ArrayList<Faculty>(List.of(faculty1, faculty2, faculty3));

        when(facultyRepository.findAll()).thenReturn(faculties);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/findAll") //send
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$[0].name").value("One"))
                .andExpect(jsonPath("$[0].color").value("111"))
                .andExpect(jsonPath("$[1].name").value("Two"))
                .andExpect(jsonPath("$[1].color").value("222"))
                .andExpect(jsonPath("$[2].name").value("Three"))
                .andExpect(jsonPath("$[2].color").value("333"));
    }

    @Test
    public void findAllStudentsByIdTest() throws Exception {

        Faculty faculty2 = new Faculty("Two", "222", 2L);

        Student student2 = new Student("Ann", 21, 2L);
        student2.setFaculty(faculty2);
        Student student3 = new Student("Nick", 22, 3L);
        student3.setFaculty(faculty2);

        List<Student> students2 = new ArrayList<>(List.of(student2, student3));
        Long facultyId = 2L;

        when(facultyRepository.findAllStudentsById(any(Long.class))).thenReturn(students2);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/{facultyId}/findAllStudentsById", facultyId) //send
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$[0].name").value("Ann"))
                .andExpect(jsonPath("$[0].age").value(21))
                .andExpect(jsonPath("$[1].name").value("Nick"))
                .andExpect(jsonPath("$[1].age").value(22));

    }

    @Test
    public void removeFacultyByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/{id}/remove", 1L) //send
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); //receive

    }

    @Test
    public void updateFacultyTest() throws Exception {
        Faculty faculty1 = new Faculty("One", "111", 1L);
        faculty1.setId(1L);
        Faculty faculty5 = new Faculty("Five", "555", 1L);
        faculty5.setId(1L);


        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty1));
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty5);

        ObjectMapper objectMapper = new ObjectMapper();
        String facultyJson = objectMapper.writeValueAsString(faculty5);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty/update")
                        .content(facultyJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.name").value("Five"))
                .andExpect(jsonPath("$.color").value("555"));
    }

    @Test
    public void findIdOnlyTest() throws Exception {
        Faculty faculty1 = new Faculty("One", "111", 1L);
        faculty1.setId(1L);

        when(facultyRepository.findFacultyById(any(Long.class))).thenReturn(faculty1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/find")
                        .param("id", "1")
                        .param("name", " ")
                        .param("color", " ")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1.0))
                .andExpect(jsonPath("$.name").value("One"))
                .andExpect(jsonPath("$.color").value("111"));
    }

    @Test
    public void findNameOnlyTest() throws Exception {
        Faculty faculty1 = new Faculty("One", "111", 1L);
        faculty1.setId(1L);

        when(facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(any(String.class), any(String.class))).thenReturn(faculty1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/find") //send
                        .param("id", "0")
                        .param("name", "One")
                        .param("color", " ")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("One"))
                .andExpect(jsonPath("$.color").value("111"));

    }

    @Test
    public void findColorOnlyTest() throws Exception {
        Faculty faculty1 = new Faculty("One", "111", 1L);
        faculty1.setId(1L);

        when(facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(any(String.class), any(String.class))).thenReturn(faculty1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/find") //send
                        .param("id", "0")
                        .param("name", " ")
                        .param("color", "111")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("One"))
                .andExpect(jsonPath("$.color").value("111"));

    }

}