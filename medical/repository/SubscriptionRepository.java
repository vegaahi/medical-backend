package com.medical.repository;

import com.medical.entity.Subscription;
import com.medical.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    // Find subscriptions by customer email
    List<Subscription> findByCustomerId_Email(String email);

    // Find subscriptions by customer email and subscription type
    List<Subscription> findByCustomerId_EmailAndSubscriptionType(String email, String subscriptionType);

    // Find subscriptions by customer and chapterId (for chapter subscriptions)
    List<Subscription> findByCustomerId_EmailAndChapterId(String email, Long chapterId);
}
