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
    private HomeopathicDoctorRepository homeopathicDoctorRepository;

    public List<HomeopathicDoctorEntity> getAllDoctors() {
        return homeopathicDoctorRepository.findAll();
    }

    public Optional<HomeopathicDoctorEntity> getDoctorById(Long id) {
        return homeopathicDoctorRepository.findById(id);
    }

    public HomeopathicDoctorEntity saveDoctor(HomeopathicDoctorEntity doctor) {
        return homeopathicDoctorRepository.save(doctor);
    }

    public void deleteDoctor(Long id) {
        homeopathicDoctorRepository.deleteById(id);
    }
}
