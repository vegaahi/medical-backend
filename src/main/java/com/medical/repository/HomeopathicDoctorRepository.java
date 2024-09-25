package com.medical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medical.entity.HomeopathicDoctorEntity;
@Repository
public interface HomeopathicDoctorRepository extends JpaRepository<HomeopathicDoctorEntity, Long>{

}
