package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAgeBetween(int min, int max);

    List<Student> findByNameContainingIgnoreCase(String name);

    List<Student> findByAgeLessThan(int age);

    List<Student> findAllByOrderOfAge();

}
