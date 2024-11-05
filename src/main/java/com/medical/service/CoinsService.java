package com.medical.service;

import com.medical.entity.Coins;
import com.medical.repository.CoinsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CoinsService {

    private final CoinsRepository coinsRepository;

    @Autowired
    public CoinsService(CoinsRepository coinRepository) {
        this.coinsRepository = coinRepository;
    }

    public List<Coins> getAllCoins() {
        return coinsRepository.findAll();
    }

    public Optional<Coins> getCoinById(Long id) {
        return coinsRepository.findById(id);
    }

    public List<Coins> getCoinsByCustomerId(Long customerId) {
        return coinsRepository.findByCustomerId(customerId);
    }

    public List<Coins> getCoinsByCustomerIdAndDate(Long customerId, LocalDate date) {
        return coinsRepository.findByCustomerIdAndDate(customerId, date);
    }

    public Coins saveCoin(Coins coins) {
        return coinsRepository.save(coins);
    }

    public void deleteCoin(Long id) {
        coinsRepository.deleteById(id);
    }
}
