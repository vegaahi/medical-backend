package com.medical.repository;

import com.medical.entity.HomeopathicDoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeopathicDoctorRepository extends JpaRepository<HomeopathicDoctorEntity, Long> {
    // You can add custom query methods here if needed
}
