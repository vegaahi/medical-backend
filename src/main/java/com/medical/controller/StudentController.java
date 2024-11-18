package com.medical.controller;


import com.medical.entity.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.medical.entity.Student;
import com.medical.service.CustomerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class StudentController {

    private final CustomerService customersService;

    @Autowired
    public StudentController(CustomerService customersService) {
        this.customersService = customersService;
    }

    // Endpoint to create a new student this is registration 
    @PostMapping("/api/Student/post")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = customersService.saveStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    // Endpoint to get a student by ID for customers
    @GetMapping("/customers/student/{id}")
    public ResponseEntity<Student> getStudentByIdForCustomers(@PathVariable Long id) {
        Optional<Student> student = customersService.getStudentById(id);
        return student.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint to get a student by ID for admins
    @GetMapping("/admins/student/{id}")
    public ResponseEntity<Student> getStudentByIdForAdmins(@PathVariable Long id) {
        Optional<Student> student = customersService.getStudentById(id);
        return student.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint to get all customers
    @GetMapping("/admins/customers")
    public ResponseEntity<List<Customers>> getAllCustomers() {
        List<Customers> customers = customersService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    // Endpoint to get all Student
    @GetMapping("/admins/students")
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> Student = customersService.getAllStudents();
        return ResponseEntity.ok(Student);
    }

    // Endpoint to delete a student by ID
    @DeleteMapping("/admins/student/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        customersService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
