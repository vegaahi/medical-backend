package com.medical.controller;

import com.medical.entity.NriDoctorEntity;
import com.medical.service.NriDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nri-doctors")
public class NriDoctorController {

    @Autowired
    private NriDoctorService nriDoctorService;

    @GetMapping
    public List<NriDoctorEntity> getAllNriDoctors() {
        return nriDoctorService.getAllNriDoctors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NriDoctorEntity> getNriDoctorById(@PathVariable Long id) {
        Optional<NriDoctorEntity> doctor = nriDoctorService.getNriDoctorById(id);
        return doctor.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public NriDoctorEntity createNriDoctor(@RequestBody NriDoctorEntity doctor) {
        return nriDoctorService.saveNriDoctor(doctor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNriDoctor(@PathVariable Long id) {
        nriDoctorService.deleteNriDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
