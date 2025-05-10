package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("Select Count FROM Student")
    int countAllStudents();

    @Query("Select Average Age From Student")
    double getAverageAgeStudents();
}

