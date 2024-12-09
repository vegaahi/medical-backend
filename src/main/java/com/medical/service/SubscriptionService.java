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

    public Subscription createMonthlySubscription(String email) {
        Customers customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found with email: " + email));
        Subscription subscription = Subscription.builder()
                .customerId(customer)
                .subscriptionType("MONTHLY")
                .amount(30.0)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(30))
                .build();
        return subscriptionRepository.save(subscription);
    }

    public Subscription createChapterSubscription(String email, Long chapterId) {
        Customers customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found with email: " + email));
        Subscription subscription = Subscription.builder()
                .customerId(customer)
                .subscriptionType("CHAPTER")
                .chapterId(chapterId)
                .amount(10.0)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(30))
                .build();
        return subscriptionRepository.save(subscription);
    }

    public List<Subscription> getSubscriptionsByCustomer(String email) {
        return subscriptionRepository.findByCustomerId_Email(email);
    }

    public boolean hasAccessToChapter(String email, Long chapterId) {
        List<Subscription> subscriptions = subscriptionRepository.findByCustomerId_Email(email);
        return subscriptions.stream()
                .anyMatch(subscription -> subscription.getSubscriptionType().equals("MONTHLY") ||
                        (subscription.getSubscriptionType().equals("CHAPTER") && subscription.getChapterId().equals(chapterId)));
    }
}
