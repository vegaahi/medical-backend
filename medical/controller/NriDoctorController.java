package com.medical.controller;

import com.medical.entity.NriDoctorEntity;
import com.medical.service.NriDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping
public class NriDoctorController {

    @Autowired
    private NriDoctorService nriDoctorService;

    @GetMapping("/admins/nri-doctors")
    public List<NriDoctorEntity> getAllNriDoctors() {
        return nriDoctorService.getAllNriDoctors();
    }

    @GetMapping("/customers/nri-doctors/{id}")
    public ResponseEntity<NriDoctorEntity> getNriDoctorByIdByCustomer(@PathVariable Long id) {
        Optional<NriDoctorEntity> doctor = nriDoctorService.getNriDoctorById(id);
        return doctor.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/admins/nri-doctors/{id}")
    public ResponseEntity<NriDoctorEntity> getNriDoctorByIdByAdmin(@PathVariable Long id) {
        Optional<NriDoctorEntity> doctor = nriDoctorService.getNriDoctorById(id);
        return doctor.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/api/nri-doctors/post")
    public NriDoctorEntity createNriDoctor(@RequestBody NriDoctorEntity doctor) {
        return nriDoctorService.saveNriDoctor(doctor);
    }

    @DeleteMapping("/admins/nri-doctors/delete/{id}")
    public ResponseEntity<Void> deleteNriDoctor(@PathVariable Long id) {
        nriDoctorService.deleteNriDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
