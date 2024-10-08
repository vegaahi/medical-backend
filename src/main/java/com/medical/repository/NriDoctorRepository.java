package com.medical.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medical.entity.NriDoctorEntity;

@Repository

public interface NriDoctorRepository extends JpaRepository<NriDoctorEntity, Long> {

}
