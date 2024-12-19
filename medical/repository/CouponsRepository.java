package com.medical.repository;

import com.medical.entity.Coupons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponsRepository extends JpaRepository<Coupons, Long> {
    List<Coupons> findByCustomersId(Long customerId);
}
