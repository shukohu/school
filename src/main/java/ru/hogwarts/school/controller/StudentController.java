package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
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
        return studentService.update(999L, student);
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

    @GetMapping("/count")
    public int countStudents() {
        return studentService.getStudentCount();
    }

    @GetMapping("/averageAge")
    public double getAverageAge() {
        return studentService.getAverageAge();
    }

    @GetMapping("/names_start_with_a")
    public List<String> getNameStartWithA() {
        return studentService.getNameStartWithA();
    }

    @GetMapping
    public long getFastSum() {
        return studentService.calculateSum();
    }

    @GetMapping("/print_parallel")
    public void printStudentsInParallel() {
        List<Student> students = studentService.getAllStudents();

        System.out.println(students.get(0).getName());
        System.out.println(students.get(1).getName());

        new Thread(() -> {
            System.out.println(students.get(2).getName());
            System.out.println(students.get(3).getName());
        }).start();

        new Thread(() -> {
            System.out.println(students.get(4).getName());
            System.out.println(students.get(5).getName());
        }).start();
    }

    @GetMapping("/print_synchronized")
    public void printStudentsSynchronized() {
        List<Student> students = studentService.getAllStudents();

        System.out.println(students.get(0).getName());
        System.out.println(students.get(1).getName());

        new Thread(() -> {
            System.out.println(students.get(2).getName());
            System.out.println(students.get(3).getName());
        }).start();

        new Thread(() -> {
            System.out.println(students.get(4).getName());
            System.out.println(students.get(5).getName());
        }).start();
    }

    private synchronized void printStudentNameSynchronized(String name) {
        System.out.println(name);
    }
}
