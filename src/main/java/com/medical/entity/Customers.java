package com.medical.entity;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) 
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fullName;
    @Column(unique = true)
    private String email;
    private String mobileNumber; // Phone numbers as String
    private LocalDate dob; // Date of birth should use LocalDate

    private String password; // Password should be String
    private String confirmPassword; // Same for confirm_password

    private String permanentCountry; // Using camelCase for field names
    private String permanentState;
    private String permanentCity;
    private String permanentAddressLine;

    private String currentCountry;
    private String currentState;
    private String currentCity;
    private String currentAddressLine;
    private String alternatePhoneNumber; // Phone number as String
   
    @Column(name = "current_streak")
    private int currentStreak = 0;

    @Column(name = "last_login")
    private LocalDate lastLogin;

    @Column(name = "total_coins")
    private int totalCoins = 0;

    @Column(name = "total_tokens")
    private int totalTokens = 0;

    @Column(name = "total_coupons")
    private int totalCoupons = 0;

     //One User can have many coins
    @OneToMany(mappedBy = "customers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Coins> coins;
    // One User can have many tokens
    @OneToMany(mappedBy = "customers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Token> tokens;

    // One User can have many coupons
    @OneToMany(mappedBy = "customers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Coupons> coupons;

    @Enumerated(EnumType.STRING)
    private CustomerType customerType; // Enum to differentiate types


    public enum CustomerType {
        STUDENT, HOMEOPATHICDOCTORENTITY, NRIDOCTORENTITY
    }
}
