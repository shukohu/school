package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import java.util.*;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository repository) {
        this.studentRepository = repository;

    }public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getById (Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    public Student update(long l, Student student) {
        return studentRepository.save(student);
    }
    public void delete(Long id) {
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
        return studentRepository.getAverageAgeStudents();
    }
}
