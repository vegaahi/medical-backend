package com.medical.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "coins")
@Getter
@Setter
@NoArgsConstructor
public class CoinsEntity {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false)
	    private Long CustomerId;

	    @Column(nullable = false)
	    private LocalDate date;

	    @Column(nullable = false)
	    private int totalTimeSpent; // in minutes

	    @Column(nullable = false)
	    private boolean coinAwarded; // Whether the user has received a coin for the day

}
