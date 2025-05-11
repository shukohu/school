package ru.hogwarts.school.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Faculty;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerRestTemplateTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCreateFaculty() {
        Faculty faculty = new Faculty();
        faculty.setName("Gryffindor");
        faculty.setColor("Red");

        ResponseEntity<Faculty> response = restTemplate.postForEntity("/faculty", faculty, Faculty.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().getId());
    }

    @Test
    void testCreateFacultyBadRequest() {
        Faculty faculty = new Faculty();
        faculty.setName("");
        faculty.setColor("");

        ResponseEntity<Faculty> response = restTemplate.postForEntity("/faculty", faculty, Faculty.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testGetFacultyById() {
        ResponseEntity<Faculty> response = restTemplate.getForEntity("/faculty/1", Faculty.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Hufflepuff", response.getBody().getName());
        assertEquals("Yellow", response.getBody().getColor);
    }

    @Test
    void testGetFacultyByIdNotFound() {
        ResponseEntity<Faculty> response = restTemplate.getForEntity("faculty/999", Faculty.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testUpdateFaculty() {
        Faculty faculty = new Faculty();
        faculty.setName("Ravenclaw");
        faculty.setColor("Blue");
        restTemplate.put("faculty/1", faculty);
        ResponseEntity<Faculty> response = restTemplate.getForEntity("/faculty/1", Faculty.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Ravenclaw", response.getBody().getName());
        assertEquals("Blue", response.getBody().getColor);
    }

    @Test
    void testUpdateFacultyNotFound() {
        Faculty faculty = new Faculty();
        faculty.setName("");
        faculty.setColor("");
        restTemplate.put("faculty/999", faculty);
        ResponseEntity<Faculty> response = restTemplate.getForEntity("faculty/999", Faculty.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteFaculty() {
        restTemplate.delete("faculty/1");
        ResponseEntity<Faculty> response = restTemplate.getForEntity("faculty/1", Faculty.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteFacultyNotFound() {
        restTemplate.delete("faculty/999");
        ResponseEntity<Faculty> response = restTemplate.getForEntity("faculty/999", Faculty.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
