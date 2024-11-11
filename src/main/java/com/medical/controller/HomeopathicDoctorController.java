package com.medical.controller;

import com.medical.entity.HomeopathicDoctorEntity;
import com.medical.service.HomeopathicDoctorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class HomeopathicDoctorController {

    @Autowired
    private HomeopathicDoctorService homeopathicDoctorService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/admins/homeopathic-doctors")
    public List<HomeopathicDoctorEntity> getAllDoctors() {
        return homeopathicDoctorService.getAllDoctors();
    }

    @GetMapping("/customers/homeopathic-doctors/{id}")
    public ResponseEntity<HomeopathicDoctorEntity> getDoctorByIdByCustomer(@PathVariable Long id) {
        Optional<HomeopathicDoctorEntity> doctor = homeopathicDoctorService.getDoctorById(id);
        return doctor.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/admins/homeopathic-doctors/{id}")
    public ResponseEntity<HomeopathicDoctorEntity> getDoctorByIdByAdmin(@PathVariable Long id) {
        Optional<HomeopathicDoctorEntity> doctor = homeopathicDoctorService.getDoctorById(id);
        return doctor.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/api/homeopathic-doctors/post")
    public HomeopathicDoctorEntity createDoctor(@RequestBody HomeopathicDoctorEntity doctor) {

        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        doctor.setConfirmPassword(passwordEncoder.encode(doctor.getConfirmPassword()));
        return homeopathicDoctorService.saveDoctor(doctor);
    }

    @DeleteMapping("/admins/homeopathic-doctors/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        homeopathicDoctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
