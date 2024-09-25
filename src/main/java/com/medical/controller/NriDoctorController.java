package com.medical.controller;

import com.medical.entity.NriDoctorEntity;
import com.medical.service.NriDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nri-doctors")
public class NriDoctorController {

    private final NriDoctorService nriDoctorService;

    @Autowired
    public NriDoctorController(NriDoctorService nriDoctorService) {
        this.nriDoctorService = nriDoctorService;
    }

    
    @GetMapping
    public List<NriDoctorEntity> getAllNriDoctors() {
        return nriDoctorService.getAllNriDoctors();
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<NriDoctorEntity> getNriDoctorById(@PathVariable Long id) {
        return nriDoctorService.getNriDoctorById(id)
                .map(doctor -> ResponseEntity.ok().body(doctor))
                .orElse(ResponseEntity.notFound().build());
    }

  
    @PostMapping
    public NriDoctorEntity createNriDoctor(@RequestBody NriDoctorEntity nriDoctorEntity) {
        return nriDoctorService.createNriDoctor(nriDoctorEntity);
    }

   
    @PutMapping("/{id}")
    public ResponseEntity<NriDoctorEntity> updateNriDoctor(@PathVariable Long id, @RequestBody NriDoctorEntity nriDoctorEntityDetails) {
        return ResponseEntity.ok(nriDoctorService.updateNriDoctor(id, nriDoctorEntityDetails));
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNriDoctor(@PathVariable Long id) {
        nriDoctorService.deleteNriDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
