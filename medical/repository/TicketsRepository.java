package com.medical.repository;

import com.medical.entity.Customers;
import com.medical.entity.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketsRepository extends JpaRepository<Tickets, Long> {
    List<Tickets> findByStatus(String status);
    List<Tickets> findByPriority(String priority);
	List<Tickets> findByEmail(String email);
	
	List<Tickets> findByCustomerId(Customers customer);
}
