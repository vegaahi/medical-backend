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

    public NriDoctorEntity createNriDoctor(NriDoctorEntity nriDoctorEntity) {
        return nriDoctorRepository.save(nriDoctorEntity);
    }

    public NriDoctorEntity updateNriDoctor(Long id, NriDoctorEntity nriDoctorEntityDetails) {
        NriDoctorEntity nriDoctorEntity = nriDoctorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Doctor not found with id " + id));

        // Update the fields
        nriDoctorEntity.setFullName(nriDoctorEntityDetails.getFullName());
        nriDoctorEntity.setEmail(nriDoctorEntityDetails.getEmail());
        nriDoctorEntity.setMobileNumber(nriDoctorEntityDetails.getMobileNumber());
        nriDoctorEntity.setDob(nriDoctorEntityDetails.getDob());
        nriDoctorEntity.setPassword(nriDoctorEntityDetails.getPassword());
        nriDoctorEntity.setConfirmPassword(nriDoctorEntityDetails.getConfirmPassword());
        nriDoctorEntity.setPermanentCountry(nriDoctorEntityDetails.getPermanentCountry());
        nriDoctorEntity.setPermanentState(nriDoctorEntityDetails.getPermanentState());
        nriDoctorEntity.setPermanentCity(nriDoctorEntityDetails.getPermanentCity());
        nriDoctorEntity.setPermanentAddressLine(nriDoctorEntityDetails.getPermanentAddressLine());
        nriDoctorEntity.setCurrentCountry(nriDoctorEntityDetails.getCurrentCountry());
        nriDoctorEntity.setCurrentState(nriDoctorEntityDetails.getCurrentState());
        nriDoctorEntity.setCurrentCity(nriDoctorEntityDetails.getCurrentCity());
        nriDoctorEntity.setCurrentAddressLine(nriDoctorEntityDetails.getCurrentAddressLine());
        nriDoctorEntity.setRegistrationNumber(nriDoctorEntityDetails.getRegistrationNumber());
        nriDoctorEntity.setStateRegistered(nriDoctorEntityDetails.getStateRegistered());
        nriDoctorEntity.setRegistrationCouncil(nriDoctorEntityDetails.getRegistrationCouncil());
        nriDoctorEntity.setCountryRegistrationWith(nriDoctorEntityDetails.getCountryRegistrationWith());
        nriDoctorEntity.setInstitutionAttendedForHomeopathy(nriDoctorEntityDetails.getInstitutionAttendedForHomeopathy());
        nriDoctorEntity.setCurrentJob(nriDoctorEntityDetails.getCurrentJob());
        nriDoctorEntity.setAlternateMobileNumber(nriDoctorEntityDetails.getAlternateMobileNumber());

        return nriDoctorRepository.save(nriDoctorEntity);
    }

    public void deleteNriDoctor(Long id) {
        NriDoctorEntity nriDoctorEntity = nriDoctorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Doctor not found with id " + id));
        nriDoctorRepository.delete(nriDoctorEntity);
    }
}
