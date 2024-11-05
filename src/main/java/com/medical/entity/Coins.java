package com.medical.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "coins")
@Getter
@Setter
@NoArgsConstructor
public class Coins {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id") // Foreign key to Customers table
    private Customers customer; // Reference to Customers entity

    private int amount; // Amount of coins or some other relevant field
    private LocalDate date; // Date of coin allocation or related date

    // Add any additional fields as necessary
}
