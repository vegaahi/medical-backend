package com.medical.controller;

import com.medical.entity.HomeopathicDoctorEntity;
import com.medical.service.HomeopathicDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/homeopathic-doctors")
public class HomeopathicDoctorController {

    @Autowired
    private HomeopathicDoctorService homeopathicDoctorService;

    @GetMapping
    public List<HomeopathicDoctorEntity> getAllDoctors() {
        return homeopathicDoctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HomeopathicDoctorEntity> getDoctorById(@PathVariable Long id) {
        Optional<HomeopathicDoctorEntity> doctor = homeopathicDoctorService.getDoctorById(id);
        return doctor.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public HomeopathicDoctorEntity createDoctor(@RequestBody HomeopathicDoctorEntity doctor) {
        return homeopathicDoctorService.saveDoctor(doctor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        homeopathicDoctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
