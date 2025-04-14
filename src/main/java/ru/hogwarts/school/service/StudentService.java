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

    public Student update(Long id, Student student) {
        Optional<Student> optional = repository.findById(id);
        if (optional.isPresent()) {
            Student updated = optional.get();
            updated.setName(student.getName());
            updated.setAge(student.getAge());
            return repository.save(updated);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Student> findByAge(int age) {
        return repository.findByAge(age);
    }
}
