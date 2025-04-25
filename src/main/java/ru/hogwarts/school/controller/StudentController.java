package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("/{id}")
    public Student get(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @PutMapping
    public Student update(@RequestBody Student student) {
        return studentService.update(student);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studentService.getById(id);
    }

    @GetMapping("/age")
    public List<Student> getByAgeRange(@RequestParam int min, @RequestParam int max) {
        return studentService.findByAgeBetween(min, max);
    }

    @GetMapping("/by_name")
    public List<Student> getByNameContains(@RequestParam String letter) {
        return studentService.getByNameContains(letter);
    }

    @GetMapping("/age_less_than_id")
    public List<Student> getByAgeLessThanId() {
        return studentService.getByAgeLessThanId();
    }

    @GetMapping("/sort_by_age")
    public List<Student> sortByAge() {
        return studentService.sortByAge();
    }

    @GetMapping("/{id}/faculty")
    public Faculty getFacultyOfStudent(@PathVariable Long id) {
        Student student = studentService.getById(id);
        return student != null ? student.getFaculty() : null;
    }
}
