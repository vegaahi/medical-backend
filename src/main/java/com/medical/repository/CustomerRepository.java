package com.medical.repository;





import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medical.entity.Customers;

import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {
    // Custom query methods if required
    Optional<Customers> findByEmail(String email);
}
