package com.medical.controller;

import com.medical.entity.Subscription;
import com.medical.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    // Create Monthly Subscription
    @PostMapping("/customers/subscriptions/monthly/{email}")
    public ResponseEntity<Subscription> createMonthlySubscription(@PathVariable String email) {
        Subscription subscription = subscriptionService.createMonthlySubscription(email);
        return ResponseEntity.ok(subscription);
    }

    // Create Chapter Subscription
    @PostMapping("/customers/subscriptions/chapter/{email}/{chapterId}")
    public ResponseEntity<Subscription> createChapterSubscription(@PathVariable String email, @PathVariable Long chapterId) {
        Subscription subscription = subscriptionService.createChapterSubscription(email, chapterId);
        return ResponseEntity.ok(subscription);
    }

    // Get Subscriptions by Customer Email
    @GetMapping("/customers/subscriptions/{email}")
    public ResponseEntity<List<Subscription>> getSubscriptionsByCustomer(@PathVariable String email) {
        List<Subscription> subscriptions = subscriptionService.getSubscriptionsByCustomer(email);
        return ResponseEntity.ok(subscriptions);
    }

    // Check Access to a Specific Chapter by Customer Email
    @GetMapping("/customers/subscriptions/access/{email}/{chapterId}")
    public ResponseEntity<Boolean> hasAccessToChapter(@PathVariable String email, @PathVariable Long chapterId) {
        boolean hasAccess = subscriptionService.hasAccessToChapter(email, chapterId);
        return ResponseEntity.ok(hasAccess);
    }
}
