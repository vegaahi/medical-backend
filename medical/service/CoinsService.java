package com.medical.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.entity.Coins;
import com.medical.entity.Customers;
import com.medical.repository.CoinsRepository;
import com.medical.repository.CustomerRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class CoinsService {

    @Autowired
    private CoinsRepository coinsRepository;
    
    @Autowired
    private CustomerRepository customerRepository;

    // Method to add coins or update the existing coin count
    public void addCoins(String email, LocalDate lastDate) {
        // Check if the email exists in the database
        Coins existingRecord = coinsRepository.findByEmail(email).orElse(null);

        if (existingRecord != null) {
            // Calculate the difference in days between the stored lastDate and the new lastDate
            long daysDifference = ChronoUnit.DAYS.between(existingRecord.getLastUpdate(), lastDate);

            // If the difference exceeds 2 days, reset the coins to 0
            if (daysDifference > 2) {
                existingRecord.setCoins(0);
            } else {
                // Else, increment the coin count by 1
                if(existingRecord.getCoins()<7) {
                    existingRecord.setCoins(existingRecord.getCoins() + 1);
                }
                else{
                    try{
                        Customers customer = customerRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Chapter not found with email: " + email));
                        customer.setTotalTokens(customer.getTotalTokens()+1);
                     if (customerRepository.save(customer) != null) {
                    existingRecord.setCoins(0);
                            }
                    }
                    catch (Exception e) {
                        System.out.println(e);
                    }


                }
            }

            // Update the last update date
            existingRecord.setLastUpdate(lastDate);
            coinsRepository.save(existingRecord); // Save the updated record back to the database
        } else {
            // If email does not exist, create a new record with 1 coin and the given lastDate
        	Customers customer = customerRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Chapter not found with email: " + email));
            
        	Coins newRecord = new Coins();
        	newRecord.setCoins(1);
        	newRecord.setCustomerId(customer);
        	newRecord.setEmail(email);
        	newRecord.setLastUpdate(lastDate);
            coinsRepository.save(newRecord); // Save the new record to the database
        }
    }

    // Method to retrieve coin count by email
    public Coins getCoinsByEmail(String email) {
        // Use the repository to find the record by email
        return coinsRepository.findByEmail(email).orElse(null);
    }
}
