package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty create(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @GetMapping("/{id}")
    public Faculty get(@PathVariable Long id) {
        return facultyService.getById(id);
    }

    @PutMapping
    public Faculty update(@RequestBody Faculty faculty) {
        return facultyService.update(faculty);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
    }

    @GetMapping("/filter")
    public List<Faculty> filterByColor(@RequestParam String value) {
        return facultyService.findByNameOrColor(value);
    }

    @GetMapping("/all_faculties")
    public List<Faculty> getAllFaculties() {
        return facultyService.getAllFaculties();
    }

    @GetMapping("/find_by_name_or_color")
    public List<Faculty> FindByNameOrColor(@RequestParam String letter) {
        return facultyService.findByNameOrColor(letter);
    }
}
