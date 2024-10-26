package com.medical.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PackageList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long packageId;
    
    @Column(nullable = false, length = 100)
    private String packageName;
    
    @Column(nullable = false, length = 50)
    private String packageType;
    
    @Column(nullable = false)
    private BigDecimal amount;
    
    @Column(nullable = false)
    private BigDecimal transactionFee;
    
    @Column(nullable = false)
    private BigDecimal totalPackageAmount;
    
    @Column(nullable = false)
    private int validity;
   
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;


}
