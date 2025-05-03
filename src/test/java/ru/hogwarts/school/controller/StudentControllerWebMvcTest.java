package ru.hogwarts.school.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        mockMvc.perform(get("/student/1")).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Hermione")).andExpect(jsonPath("$.age").value(15));

    }

}
