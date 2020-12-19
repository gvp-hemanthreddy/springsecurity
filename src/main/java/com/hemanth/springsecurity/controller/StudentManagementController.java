package com.hemanth.springsecurity.controller;

import com.hemanth.springsecurity.model.Student;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {
    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "Hemanth"),
            new Student(2, "Mahidhar"),
            new Student(3, "Test")
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<Student> getAllStudents() {
        System.out.println("getAllStudents");
        return STUDENTS;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('STUDENT:WRITE')")
    public void registerNewStudent(@RequestBody Student student) {
        System.out.println("registerNewStudent");
        System.out.println(student);
    }

    @PutMapping("{studentId}")
    @PreAuthorize("hasAuthority('STUDENT:WRITE')")
    public void updateStudent(@PathVariable("studentId") int studentId, @RequestBody Student student) {
        System.out.println("updateStudent");
        System.out.println(String.format("%s %s", studentId, student));
    }

    @DeleteMapping("{studentId}")
    @PreAuthorize("hasAuthority('STUDENT:WRITE')")
    public void deleteStudent(@PathVariable("studentId") int studentId) {
        System.out.println("deleteStudent");
        System.out.println(studentId);
    }
}
