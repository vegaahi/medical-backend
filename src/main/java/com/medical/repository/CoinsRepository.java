package com.medical.repository;


import com.medical.entity.CoinsEntity;
import com.medical.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CoinsRepository extends JpaRepository<CoinsEntity, Long> {
    Optional<CoinsEntity> findByUserIdAndDate(Long CustomerId, LocalDate date);

	Optional<CoinsEntity> findByCustomerIdAndDate(Long customerId, LocalDate today);

	

	
}