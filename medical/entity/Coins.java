package com.medical.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Coins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int coins; // Assuming this is the field you want to keep for coin count

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonBackReference
    private Customers customerId;

//    @ManyToOne
//    @JoinColumn(name = "customer_id", nullable = false)
//    @JsonBackReference // Maps to customer
//    private Customers customerId;
    private String email;
    

    @Column(name = "last_update")
    private LocalDate lastUpdate;
   

}
