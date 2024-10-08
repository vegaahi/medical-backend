package com.medical.service;

import com.medical.entity.HomeopathicDoctorEntity;
import com.medical.repository.HomeopathicDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HomeopathicDoctorService {

    @Autowired
    private HomeopathicDoctorRepository repository;

    public HomeopathicDoctorEntity saveDoctor(HomeopathicDoctorEntity doctor) {
        return repository.save(doctor);
    }

    public List<HomeopathicDoctorEntity> getAllDoctors() {
        return repository.findAll();
    }

    public HomeopathicDoctorEntity getDoctorById(Long id) {
        Optional<HomeopathicDoctorEntity> doctor = repository.findById(id);
        return doctor.orElse(null);
    }

    public HomeopathicDoctorEntity updateDoctor(Long id, HomeopathicDoctorEntity updatedDoctor) {
        if (repository.existsById(id)) {
            updatedDoctor.setId(id);
            return repository.save(updatedDoctor);
        }
        return null;
    }

    public boolean deleteDoctor(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
