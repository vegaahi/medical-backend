package com.medical.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.entity.CoinsEntity;
import com.medical.entity.Customers;
import com.medical.entity.Student;
import com.medical.repository.CustomerRepository;
import com.medical.repository.StudentRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
@Service
public class CustomerService {

    private final CustomerRepository customersRepository;
    private final StudentRepository studentRepository;

 @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerService(CustomerRepository customersRepository,
                           StudentRepository studentRepository,
                           PasswordEncoder passwordEncoder) {
        this.customersRepository = customersRepository;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Method to save a Customer (Student)
public Student saveStudent(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setConfirmPassword(passwordEncoder.encode(student.getConfirmPassword()));
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

	public Customers getCustomerById(Long customerId) {
		// TODO Auto-generated method stub
		return customersRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Chapter not found with id: " + customerId));
	}
	
	
    public List<Customers> getAllCustomers() {
    return customersRepository.findAll();
}
}
