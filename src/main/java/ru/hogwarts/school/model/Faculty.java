package ru.hogwarts.school.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String color;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY)
    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }
}
