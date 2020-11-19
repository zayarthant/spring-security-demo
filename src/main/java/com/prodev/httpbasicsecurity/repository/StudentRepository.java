package com.prodev.httpbasicsecurity.repository;

import com.prodev.httpbasicsecurity.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
    List<Student> findAll();
}
