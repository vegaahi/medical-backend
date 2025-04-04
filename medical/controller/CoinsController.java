package com.medical.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.medical.service.CoinsService;
import com.medical.entity.Coins;

import java.time.LocalDate;

@RestController
@CrossOrigin
@RequestMapping("/coins")
public class CoinsController {

    @Autowired
    private CoinsService coinsService;

    @PostMapping("/add")
    public String addCoins(@RequestParam String email,@RequestParam String today) {
        try {
            LocalDate date = LocalDate.parse(today);
            coinsService.addCoins(email, date);
            return "Coin count updated successfully for " + email;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }


    // Endpoint to get the current coin count of a user
    @GetMapping("/get/{email}")
    public String getCoinCount(@PathVariable String email) {
        // Use the service to get the coin count for the given email
        Coins record = coinsService.getCoinsByEmail(email);
        if (record != null) {
            return "Coin count for " + email + ": " + record.getCoins();
        } else {
            return "No record found for " + email;
        }
    }
}
