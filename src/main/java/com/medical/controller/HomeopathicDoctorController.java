package com.medical.controller;

import com.medical.entity.HomeopathicDoctorEntity;
import com.medical.service.HomeopathicDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/homeopathic-doctors")
public class HomeopathicDoctorController {

    @Autowired
    private HomeopathicDoctorService homeopathicDoctorService;

    
    @PostMapping
    public ResponseEntity<HomeopathicDoctorEntity> createDoctor(@RequestBody HomeopathicDoctorEntity doctor) {
        HomeopathicDoctorEntity createdDoctor = homeopathicDoctorService.saveDoctor(doctor);
        return new ResponseEntity<>(createdDoctor, HttpStatus.CREATED);
    }

    
    @GetMapping
    public ResponseEntity<List<HomeopathicDoctorEntity>> getAllDoctors() {
        List<HomeopathicDoctorEntity> doctors = homeopathicDoctorService.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<HomeopathicDoctorEntity> getDoctorById(@PathVariable Long id) {
        HomeopathicDoctorEntity doctor = homeopathicDoctorService.getDoctorById(id);
        if (doctor != null) {
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

   
    @PutMapping("/{id}")
    public ResponseEntity<HomeopathicDoctorEntity> updateDoctor(@PathVariable Long id, @RequestBody HomeopathicDoctorEntity updatedDoctor) {
        HomeopathicDoctorEntity doctor = homeopathicDoctorService.updateDoctor(id, updatedDoctor);
        if (doctor != null) {
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

  
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        boolean isDeleted = homeopathicDoctorService.deleteDoctor(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
