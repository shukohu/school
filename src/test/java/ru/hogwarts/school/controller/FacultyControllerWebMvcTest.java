package ru.hogwarts.school.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class FacultyControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyService facultyService;
    @Test
    void testGetById()throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Gryffindor");
        faculty.setId(1L);
        faculty.setColor("Red");

        when(facultyService.getById(1L)).thenReturn(faculty);
        mockMvc.perform(get("/faculty/1")).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Gryffindor"));
    }

    @Test
    void testGetByIdNotFound() throws Exception {
        when(facultyService.getById(999L)).thenReturn(null);
        mockMvc.perform(get("/faculty/999")).andExpect(status().isNotFound());
    }
    @Test
    void createFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Slytherin");
        faculty.setColor("Green");

        when(facultyService.createFaculty(faculty)).thenReturn(faculty);
        mockMvc.perform(post("/faculty")).andExpect(status().isCreated()).andExpect(jsonPath("$.name").value("Slytherin"));
    }

    @Test
    void createStudentNotFound() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Math");
        faculty.setColor("White");


        when(facultyService.createFaculty(faculty)).thenReturn(faculty);
        mockMvc.perform(post("/faculty")).andExpect(status().isNotFound());
    }

    @Test
    void testUpdateFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Gryffindor");

        when(facultyService.update(1L, faculty)).thenReturn(faculty);

        mockMvc.perform(put("/faculties/1")).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Gryffindor"));
    }


    @Test
    void testUpdateFacultyNotFound() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(999L);
        faculty.setName("Math");
        when(facultyService.update(999L, faculty)).thenReturn(null);
        mockMvc.perform(put("/faculty/999")).andExpect(status().isNotFound());
    }
}
