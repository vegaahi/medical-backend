package com.medical.service;

import com.medical.entity.CoinsEntity;
import com.medical.entity.Customers;
import com.medical.repository.CoinsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CoinsService {

	  @Autowired
	    private CoinsRepository coinsRepository;

	    public void updateTotalTimeSpent(Long customerId, int timeSpent) {
	        LocalDate today = LocalDate.now();
	        Optional<CoinsEntity> optionalCoinsEntity = coinsRepository.findByUserCustomerIdAndDate(customerId, today);

	        CoinsEntity coinsEntity;

	        if (optionalCoinsEntity.isPresent()) {
	            // User already has an entry for today, update their time
	            coinsEntity = optionalCoinsEntity.get();
	            coinsEntity.setTotalTimeSpent(coinsEntity.getTotalTimeSpent() + timeSpent);
	        } else {
	            // No entry for today, create a new one
	            coinsEntity = new CoinsEntity();
	            coinsEntity.setCustomerId(customerId);
	            coinsEntity.setDate(today);
	            coinsEntity.setTotalTimeSpent(timeSpent);
	            coinsEntity.setCoinAwarded(false);
	        }

	        // Check if coin can be awarded
	        if (coinsEntity.getTotalTimeSpent() >= 10 && !coinsEntity.isCoinAwarded()) {
	            coinsEntity.setCoinAwarded(true); // Award the coin
	        }

	        coinsRepository.save(coinsEntity);
	    }

	    public boolean hasUserEarnedCoin(Long customerId) {
	        LocalDate today = LocalDate.now();
	        Optional<CoinsEntity> optionalCoinsEntity = coinsRepository.findByCustomerIdAndDate(customerId, today);
	        return optionalCoinsEntity.map(CoinsEntity::isCoinAwarded).orElse(false);
	    }
}
