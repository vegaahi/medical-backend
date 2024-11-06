package com.medical.service;

import com.medical.entity.Coins;
import com.medical.entity.Customers;
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
    public CoinsService(CoinsRepository coinsRepository) {
        this.coinsRepository = coinsRepository;
    }

    public List<Coins> getAllCoins() {
        return coinsRepository.findAll();
    }

    public Optional<Coins> getCoinById(Long id) {
        return coinsRepository.findById(id);
    }

    public List<Coins> getCoinsByCustomer(Customers customers) {
        return coinsRepository.findByCustomers(customers);
    }

    public List<Coins> getCoinsByCustomerAndDate(Customers customers, LocalDate date) {
        return coinsRepository.findByCustomersAndDate(customers, date);
    }

    public Coins saveCoin(Coins coins) {
        return coinsRepository.save(coins);
    }

    public void deleteCoin(Long id) {
        coinsRepository.deleteById(id);
    }
}
