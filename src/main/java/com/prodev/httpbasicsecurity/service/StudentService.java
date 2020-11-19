package com.prodev.httpbasicsecurity.service;

import com.prodev.httpbasicsecurity.entity.Student;
import com.prodev.httpbasicsecurity.repository.StudentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    private PasswordEncoder passwordEncoder;

    public StudentService(StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Long createStudent(Student student){
        Optional<Student> optionalStudent = studentRepository.findByEmail(student.getEmail());
        if(optionalStudent.isPresent()){
            throw new EntityExistsException(" User with " + student.getEmail() + " already exist!");
        }
        String encriptedPassword = passwordEncoder.encode(student.getPassword());
        student.setPassword(encriptedPassword);
        return studentRepository.save(student).getId();
    }

    public List<Student> getAll(){
        return this.studentRepository.findAll();
    }

    public Student getByEmail(String email){
        Optional<Student> optionalStudent = studentRepository.findByEmail(email);
        if(optionalStudent.isPresent()){
            return optionalStudent.get();
        }
        throw new EntityNotFoundException("User with " + email + " doesn't exist!");
    }
}
