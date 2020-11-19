package com.prodev.httpbasicsecurity.controller;

import com.prodev.httpbasicsecurity.entity.Student;
import com.prodev.httpbasicsecurity.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAll(){
        return this.studentService.getAll();
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Student student){
        Long id = studentService.createStudent(student);
        return new ResponseEntity(id, HttpStatus.CREATED);
    }


}
