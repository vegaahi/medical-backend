package com.medical.controller;

import com.medical.entity.CoinsEntity;
import com.medical.entity.Customers;
import com.medical.service.CoinsService;
import com.medical.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/coins")
public class CoinsController {


    @Autowired
    private CoinsService coinsService;

    @PostMapping("/update-time")
    public Map<String, String> updateTimeSpent(@RequestParam Long userId, @RequestParam int timeSpent) {
        coinsService.updateTotalTimeSpent(userId, timeSpent);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Time updated successfully.");
        return response;
    }

    @GetMapping("/has-earned-coin")
    public Map<String, Boolean> hasUserEarnedCoin(@RequestParam Long userId) {
        boolean hasEarnedCoin = coinsService.hasUserEarnedCoin(userId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("hasEarnedCoin", hasEarnedCoin);
        return response;
    }
}
