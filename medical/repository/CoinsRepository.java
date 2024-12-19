package com.medical.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medical.entity.Coins;

import java.util.Optional;

public interface CoinsRepository extends JpaRepository<Coins, Long> {
    Optional<Coins> findByEmail(String email);  // This should return an Optional<Coins>
}
