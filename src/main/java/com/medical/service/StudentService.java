//package com.medical.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//
//import com.medical.entity.Student;
//import com.medical.entity.StudentPrincipal;
//import com.medical.repository.StudentRepository;
//
//@Service
// public class StudentService implements UserDetailsService{
//
// private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
//
//    private StudentRepository studentRepository;
//
//    @Autowired
//    public StudentService(StudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }
//
//
//    public List<Student> getAllStudents() {
//        return studentRepository.findAll();
//    }
//
//    public Optional<Student> getStudentById(Long id) {
//
//        return studentRepository.findById(id);
//    }
//
//
//    public Student createStudent(Student student) {
//    	student.setPassword(encoder.encode(student.getPassword()));
//        return studentRepository.save(student);
//    }
//
//    public Student updateStudent(Long id, Student studentDetails) {
//        Student student = studentRepository.findById(id)
//            .orElseThrow(() -> new RuntimeException("Student not found with id " + id));
//
//        student.setFullName(studentDetails.getFullName());
//        student.setEmail(studentDetails.getEmail());
//        student.setUsername(studentDetails.getUsername());
//        student.setMobileNumber(studentDetails.getMobileNumber());
//        student.setDob(studentDetails.getDob());
//        student.setPassword(studentDetails.getPassword());
//        student.setConfirmPassword(studentDetails.getConfirmPassword());
//        student.setPermanentCountry(studentDetails.getPermanentCity());
//        student.setPermanentState(studentDetails.getPermanentState());
//        student.setPermanentCity(studentDetails.getPermanentCity());
//        student.setPermanentAddressLine(studentDetails.getPermanentAddressLine());
//        student.setCurrentCountry(studentDetails.getCurrentCountry());
//        student.setCurrentState(studentDetails.getCurrentState());
//        student.setCurrentCity(studentDetails.getCurrentCity());
//        student.setCurrentAddressLine(studentDetails.getCurrentAddressLine());
//        student.setUniversity(studentDetails.getUniversity());
//        student.setYear(studentDetails.getYear());
//        student.setAlternatePhoneNumber(studentDetails.getAlternatePhoneNumber());
//
//        return studentRepository.save(student);
//    }
//
//    public void deleteStudent(Long id) {
//        Student student = studentRepository.findById(id)
//            .orElseThrow(() -> new RuntimeException("Student not found with id " + id));
//        studentRepository.delete(student);
//    }
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Student student = studentRepository.findByUsername(username);
//		 if(student == null) {
//			 System.out.println("User not found");
//			 throw new UsernameNotFoundException("user not found");
//		 }
//		return new StudentPrincipal(student);
//	}
//
//
//
//
//}
