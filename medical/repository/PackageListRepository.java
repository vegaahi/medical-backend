package com.medical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medical.entity.PackageList;
@Repository
public interface PackageListRepository extends JpaRepository<PackageList, Long>{
 
}
