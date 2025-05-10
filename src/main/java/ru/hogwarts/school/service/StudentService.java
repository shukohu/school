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
    }

    public int getStudentCount() {
        return studentRepository.countAllStudents();
    }

    public double getAverageAge() {
        return studentRepository.getAverageAgeStudents();
    }

}
