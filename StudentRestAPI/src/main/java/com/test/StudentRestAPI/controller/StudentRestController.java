package com.test.StudentRestAPI.controller;

import com.test.StudentRestAPI.entity.Student;
import com.test.StudentRestAPI.repos.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class StudentRestController {

    private StudentRepository repo;

    @GetMapping("/getStudents")
    public List<Student> getStudens() {
        return repo.findAll();
    }

    @GetMapping("/getStudents/{id}")
    public Student getStudenById(@PathVariable("id") int id) {
        return repo.findById(id).get();
    }

    @PostMapping("/saveStudents")
    public List<Student> saveStudens(@RequestBody List<Student> student) {
        return repo.saveAll(student);
    }

    @PutMapping("/updateStudents")
    public Student updateStudens(@RequestBody Student student) {
        return repo.save(student);
    }

    @DeleteMapping("/deleteStudents/{id}")
    public void deleteStudents(@PathVariable("id") int id) {
        repo.deleteById(id);
    }
}
