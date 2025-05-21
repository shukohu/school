package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import java.util.*;

@Service
public class FacultyService {
    private static final Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private final FacultyRepository repository;

    public FacultyService(FacultyRepository repository) {
        this.repository = repository;
    }


    public Faculty createFaculty(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        return repository.save(faculty);
    }

    public Faculty getById(Long id) {
        logger.info("Was invoked method to get faculty by id = {}", id);
        return repository.findById(id).orElseThrow(()-> {
            ;
            logger.error("There is no faculty by id = {}", id);
            return new RuntimeException("Faculty not found");
        });
    }

    public Faculty update(long l, Faculty faculty) {
        logger.info("Was invoked method for update Faculty");
        return repository.save(faculty);

    }

    public void deleteFaculty(Long id) {
        logger.warn("Was invoked method for delete faculty by id = {}", id);
        repository.deleteById(id);

    }

    public List<Faculty> getAllFaculties() {
        logger.debug("Was invoked method to get all faculties");
        return repository.findAll();
    }

    public List<Faculty> findByNameOrColor(String filter) {
        logger.info("Was invoked method to find faculty by name or color = {}", filter);
        return repository.findByNameIgnoreCaseOrColor(filter, filter);
    }

    public String getLongestFacultyName() {
        return repository.findAll().stream().map(Faculty::getName).filter(Objects::nonNull).max(Comparator.comparing(String::length)).orElse("Нет факультетов");
    }
}
