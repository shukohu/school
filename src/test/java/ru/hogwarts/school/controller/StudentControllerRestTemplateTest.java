package ru.hogwarts.school.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Student;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class StudentControllerRestTemplateTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCreateStudent() {
        Student student = new Student();
        student.setName("Hermione");
        student.setAge(15);
        ResponseEntity<Student> response = restTemplate.postForEntity("/student", student, Student.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().getId());

    }

    @Test
    void getStudentById() {
        ResponseEntity<Student> response = restTemplate.getForEntity("/student/1", Student.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Hermione", response.getBody().getAge());
    }

    @Test
    void testGetStudentNotFound() {
        ResponseEntity<Student> response = restTemplate.getForEntity("/student/999", Student.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testCreateStudentBadRequest() {
        Student student = new Student();
        student.setName("");
        student.setAge(0);

        ResponseEntity<Student> response = restTemplate.postForEntity("/student", student, Student.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testUpdateStudentNotFound() {
        Student student = new Student();
        student.setName("Lord Voldemort");
        student.setAge(72);

        restTemplate.put("/student/999", Student.class);
        ResponseEntity<Student> response = restTemplate.getForEntity("/student/999", Student.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testUpdateStudent() {
        Student student = new Student();
        student.setName("Hermione Granger");
        student.setAge(16);

        restTemplate.put("/student/1", student);
        ResponseEntity<Student> response = restTemplate.getForEntity("/student/1", Student.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hermione Granger", response.getBody().getName());
        assertEquals(16, response.getBody().getAge());
    }

    @Test
    void testDeleteStudent() {
        restTemplate.delete("/student/1");
        ResponseEntity<Student> response = restTemplate.getForEntity("student/1", Student.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteNotFound() {
        restTemplate.delete("/student/999");
        ResponseEntity<Student> response = restTemplate.getForEntity("student/999", Student.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }
}
