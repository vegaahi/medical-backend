package com.medical.controller;

import com.medical.entity.Coins;
import com.medical.entity.Customers;
import com.medical.service.CoinsService;
import com.medical.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/coins")
public class CoinsController {

	@Autowired
	private final CustomerService customerservice;
	
    private final CoinsService coinsService;
   
    @Autowired
    public CoinsController(CoinsService coinsService) {
        this.customerservice = null;
		this.coinsService = coinsService;
    }

    @GetMapping
    public ResponseEntity<List<Coins>> getAllCoins() {
        List<Coins> coins = coinsService.getAllCoins();
        return ResponseEntity.ok(coins);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coins> getCoinById(@PathVariable Long id) {
        return coinsService.getCoinById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Coins>> getCoinsByCustomerId(@PathVariable Long customerId) {
          Customers customer = customerservice.getCustomerById(customerId);
    	
    	List<Coins> coins = coinsService.getCoinsByCustomer(customer);
        return ResponseEntity.ok(coins);
    }

    @GetMapping("/customer/{customerId}/date")
    public ResponseEntity<List<Coins>> getCoinsByCustomerIdAndDate(
            @PathVariable Long customerId,
            @RequestParam LocalDate date) {
    	 Customers customer = customerservice.getCustomerById(customerId);
        List<Coins> coins = coinsService.getCoinsByCustomerAndDate(customer, date);
        return ResponseEntity.ok(coins);
    }

    @PostMapping
    public ResponseEntity<Coins> createCoin(@RequestBody Coins coins) {
        Coins savedCoin = coinsService.saveCoin(coins);
        return ResponseEntity.ok(savedCoin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coins> updateCoin(@PathVariable Long id, @RequestBody Coins coins) {
        return coinsService.getCoinById(id)
                .map(existingCoin -> {
                    coins.setId(id);
                    Coins updatedCoin = coinsService.saveCoin(coins);
                    return ResponseEntity.ok(updatedCoin);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoin(@PathVariable Long id) {
        if (coinsService.getCoinById(id).isPresent()) {
            coinsService.deleteCoin(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
