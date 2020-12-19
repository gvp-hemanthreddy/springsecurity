package com.hemanth.springsecurity.controller;

import com.hemanth.springsecurity.exceptions.SpringSecurityException;
import com.hemanth.springsecurity.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "Hemanth"),
            new Student(2, "Mahidhar"),
            new Student(3, "Test")
    );

    @GetMapping("{studentId}")
    public Student getStudent(@PathVariable("studentId") int studentId) {
        return STUDENTS.stream()
                .filter(student -> student.getStudentId() == studentId)
                .findFirst()
                .orElseThrow(() -> new SpringSecurityException("Student id " + studentId + " not found"));
    }
}
