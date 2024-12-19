package com.medical.service;

import com.medical.entity.Subscription;
import com.medical.entity.Customers;
import com.medical.repository.SubscriptionRepository;
import com.medical.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // Create Monthly Subscription (30 days access to all chapters for ₹30)
    public Subscription createMonthlySubscription(String email) {
        Customers customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found with email: " + email));
        
        Subscription subscription = Subscription.builder()
                .customerId(customer)
                .subscriptionType("MONTHLY")
                .amount(30.0)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(30)) // Valid for 30 days
                .build();
        
        return subscriptionRepository.save(subscription);
    }

    // Create Chapter Subscription (Lifetime access to one chapter for ₹10)
    public Subscription createChapterSubscription(String email, Long chapterId) {
        Customers customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found with email: " + email));
        
        Subscription subscription = Subscription.builder()
                .customerId(customer)
                .subscriptionType("CHAPTER")
                .chapterId(chapterId)
                .amount(10.0)
                .startDate(LocalDate.now())
                .endDate(null) // No expiration for lifetime access
                .build();
        
        return subscriptionRepository.save(subscription);
    }

    // Get Subscriptions by Customer Email
    public List<Subscription> getSubscriptionsByCustomer(String email) {
        return subscriptionRepository.findByCustomerId_Email(email);
    }

    // Check Access to a Specific Chapter by Customer Email
    public boolean hasAccessToChapter(String email, Long chapterId) {
        List<Subscription> subscriptions = subscriptionRepository.findByCustomerId_Email(email);
        
        // Check if the customer has any valid "MONTHLY" subscription or a "CHAPTER" subscription for the specific chapter
        return subscriptions.stream()
                .anyMatch(subscription -> {
                    // If it's a "MONTHLY" subscription, check if it's within the valid period (startDate to endDate)
                    if (subscription.getSubscriptionType().equals("MONTHLY")) {
                        return !LocalDate.now().isBefore(subscription.getStartDate()) && !LocalDate.now().isAfter(subscription.getEndDate());
                    }
                    // If it's a "CHAPTER" subscription, check if the customer is subscribed to the specific chapter
                    return subscription.getSubscriptionType().equals("CHAPTER") && subscription.getChapterId().equals(chapterId);
                });
    }
}
 