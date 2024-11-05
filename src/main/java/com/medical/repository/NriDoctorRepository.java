package com.medical.repository;

import com.medical.entity.NriDoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NriDoctorRepository extends JpaRepository<NriDoctorEntity, Long> {
    // Additional query methods (if needed) can be defined here
}
