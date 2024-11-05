package com.medical.service;

import com.medical.entity.NriDoctorEntity;
import com.medical.repository.NriDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NriDoctorService {

    @Autowired
    private NriDoctorRepository nriDoctorRepository;

    public List<NriDoctorEntity> getAllNriDoctors() {
        return nriDoctorRepository.findAll();
    }

    public Optional<NriDoctorEntity> getNriDoctorById(Long id) {
        return nriDoctorRepository.findById(id);
    }

    public NriDoctorEntity saveNriDoctor(NriDoctorEntity doctor) {
        return nriDoctorRepository.save(doctor);
    }

    public void deleteNriDoctor(Long id) {
        nriDoctorRepository.deleteById(id);
    }
}
