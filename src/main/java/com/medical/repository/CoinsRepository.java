package com.medical.repository;

import com.medical.entity.Coins;
import com.medical.entity.Customers; // Import the Customer entity
import com.medical.entity.Customers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CoinsRepository extends JpaRepository<Coins, Long> {
   List<Coins> findByCustomers(Customers customers); // Use Customer entity instead of customerId
   List<Coins> findByCustomersAndDate(Customers customers, LocalDate date); // Use Customer entity and date
}
