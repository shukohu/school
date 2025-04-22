package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student createStudent(Student student) {
        return repository.save(student);
    }

    public Student getById (Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student update(Student student) {
        return repository.save(student);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Student> findByAgeBetween(int min, int max) {
        return repository.findByAgeBetween(min, max);
    }

    public List<Student> getByNameContains(String letter) {
        return repository.findByNameContainingIgnoreCase(letter);
    }
    public List<Student> getByAgeLessThanId() {
        return repository.findByAgeLessThan(100);
    }

    public List<Student> sortByAge() {
        return repository.findAllByOrderOfAge();
    }
}
