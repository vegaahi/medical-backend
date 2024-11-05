package com.medical.repository;

import com.medical.entity.Coins;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CoinsRepository extends JpaRepository<Coins, Long> {
    List<Coins> findByCustomerId(Long customerId);
    List<Coins> findByCustomerIdAndDate(Long customerId, LocalDate date);
}
