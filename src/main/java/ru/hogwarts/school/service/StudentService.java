package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import java.util.*;
import java.util.stream.LongStream;

@Service
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;


    public StudentService(StudentRepository repository) {
        this.studentRepository = repository;

    }public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public Student getById (Long id) {
        logger.info("Was invoked method for get student by id = {}", id);
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> getAllStudents() {
        logger.debug("Was invoked method to get all students");
        return studentRepository.findAll();
    }
    public Student update(long l, Student student) {
        logger.info("Was invoked method for update student");
        return studentRepository.save(student);
    }
    public void delete(Long id) {
        logger.warn("Delete student by id = {}", id);
        studentRepository.deleteById(id);
    }

    public List<Student> findByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public List<Student> getByNameContains(String letter) {
        return studentRepository.findByNameContainingIgnoreCase(letter);
    }
    public List<Student> getByAgeLessThanId() {
        return studentRepository.findByAgeLessThan(100);
    }

    public List<Student> sortByAge() {
        return studentRepository.findAllByOrderOfAge();
    }

    public int getStudentCount() {
        return studentRepository.countAllStudents();
    }

    public double getAverageAge() {
        return studentRepository.findAll().stream().mapToInt(Student::getAge).average().orElse(0);
    }

    public List<String> getNameStartWithA() {
        return studentRepository.findAll().stream().map(Student::getName).filter(Objects::nonNull).map(String::toUpperCase).filter(name -> name.startsWith("A")).sorted().toList();
    }

    public long calculateSum() {
        return LongStream.rangeClosed(1, 1_000_000).parallel().sum();
    }
}
