package com.medical.entity;

import java.time.LocalDate;

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
public class HomeopathicDoctorEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // Changed to Long for auto-generation

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber; // Phone number as String

    @Column(name = "dob")
    private LocalDate dob; // Date of birth as LocalDate

    @Column(name = "password")
    private String password; // Password as String

    @Column(name = "confirm_password")
    private String confirmPassword; // Same for confirm_password

    @Column(name = "p_country")
    private String permanentCountry; // Using camelCase for fields

    @Column(name = "p_state")
    private String permanentState;

    @Column(name = "p_city")
    private String permanentCity;

    @Column(name = "p_address_line")
    private String permanentAddressLine;

    @Column(name = "c_country")
    private String currentCountry;

    @Column(name = "c_state")
    private String currentState;

    @Column(name = "c_city")
    private String currentCity;

    @Column(name = "c_address_line")
    private String currentAddressLine;

    @Column(name = "qualification")
    private String qualification;

    @Column(name = "registration_number")
    private String registrationNumber; // Registration number as String for flexibility

    @Column(name = "state_registered")
    private String stateRegistered;

    @Column(name = "university")
    private String university;

    @Column(name = "college")
    private String college;

    @Column(name = "current_job")
    private String currentJob;

    @Column(name = "alternate_mobile_number")
    private String alternateMobileNumber; // Phone number as String
}
