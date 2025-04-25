package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import java.util.*;

@Service
public class FacultyService {
    private final FacultyRepository repository;

    public FacultyService(FacultyRepository repository) {
        this.repository = repository;
    }


    public Faculty createFaculty(Faculty faculty) {
        return repository.save(faculty);
    }

    public Faculty getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Faculty update(Faculty faculty) {
        return repository.save(faculty);

    }

    public void deleteFaculty(Long id) {
        repository.deleteById(id);

    }

    public List<Faculty> getAllFaculties() {
        return repository.findAll();
    }

    public List<Faculty> findByNameOrColor(String filter) {
        return repository.findByNameIgnoreCaseOrColor(filter, filter);
    }
}
