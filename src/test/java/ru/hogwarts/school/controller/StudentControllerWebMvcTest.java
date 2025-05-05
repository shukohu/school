package ru.hogwarts.school.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentService studentService;

    @Test
    void testGetById()throws Exception{
        Student student = new Student();
        student.setId(1L);
        student.setName("Hermione");
        student.setAge(15);


        when(studentService.getById(1L)).thenReturn(student);
        mockMvc.perform(get("/student/1")).andExpect(status().isOk()).andExpect(jsonPath("$.name")
                .value("Hermione")).andExpect(jsonPath("$.age").value(15));

    }

    @Test
    void testGetByIdNotFound() throws Exception {
        when(studentService.getById(999L)).thenReturn(null);
        mockMvc.perform(get("student/999")).andExpect(status().isNotFound());
    }

    @Test
    void createStudent() throws Exception {
        Student student = new Student();
        student.setId(2L);
        student.setName("Harry");
        student.setAge(16);

        when(studentService.createStudent(student)).thenReturn(student);
        mockMvc.perform(post("/student/2")).andExpect(status().isCreated()).andExpect(jsonPath("$.name").value("Harry")).andExpect(jsonPath("$.age").value(16));
    }

    @Test
    void createStudentNotFound() throws Exception {
        Student student = new Student();
        student.setName("Lord Voldemort");
        student.setId(999L);

        when(studentService.createStudent(student)).thenReturn(student);
        mockMvc.perform(post("student/999")).andExpect(status().isNotFound());
    }

    @Test
    void updateStudent() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Hermione Granger");
        student.setAge(16);

        when(studentService.update(1L, student)).thenReturn(student);
        mockMvc.perform(put("/student/1")).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Hermione Granger")).andExpect(jsonPath("$.age").value(16));

    }

    @Test
    void updateStudentNotFound() throws Exception {
        Student student = new Student();
        student.setId(999L);
        student.setName("");
        student.setAge(999);
        when(studentService.update(999L, student)).thenReturn(null);
        mockMvc.perform(put("/student/999")).andExpect(status().isNotFound());
    }
}
