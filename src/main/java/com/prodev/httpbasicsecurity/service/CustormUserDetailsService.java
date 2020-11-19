package com.prodev.httpbasicsecurity.service;

import com.prodev.httpbasicsecurity.entity.Student;
import com.prodev.httpbasicsecurity.pojo.UserDetailInfo;
import com.prodev.httpbasicsecurity.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Qualifier("MyUserDetails")
public class CustormUserDetailsService implements UserDetailsService {

    private StudentRepository studentRepository;

    public CustormUserDetailsService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetailInfo userDetailInfo = null;
        Optional<Student> optionalStudent = studentRepository.findByEmail(email);
        if(optionalStudent.isPresent()){
            Student student = optionalStudent.get();
            userDetailInfo = new UserDetailInfo(student.getName(),student.getPassword(), student.getRole());
        }
        return userDetailInfo;
    }
}
