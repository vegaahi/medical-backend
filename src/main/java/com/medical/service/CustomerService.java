package com.medical.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.entity.Student;
import com.medical.repository.CustomerRepository;
import com.medical.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customersRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public CustomerService(CustomerRepository customersRepository,
                                StudentRepository studentRepository) {
        this.customersRepository = customersRepository;
        this.studentRepository = studentRepository;
    }

    // Method to save a Customer (Student)
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // Method to get a Student by ID
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    // Method to get all Students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Method to delete a Student by ID
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
