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
public class NriDoctorEntity {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private Long id; // Changed to Long for ID generation

	    
	    @Column(name = "full_name")
	    private String fullName;

	    
	    @Column(name = "email")
	    private String email;


	    @Column(name = "mobile_number")
	    private String mobileNumber; // Changed to String for phone number

	   
	    @Column(name = "dob")
	    private LocalDate dob; // Use LocalDate for date of birth

	  
	    @Column(name = "password")
	    private String password; // Changed to String for passwords

	  
	    @Column(name = "confirm_password")
	    private String confirmPassword; // Changed to String

	  
	    @Column(name = "p_country")
	    private String permanentCountry; // Renamed to camelCase

	  
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

	    
	    @Column(name = "registration_number")
	    private String registrationNumber; // Changed to String

	    
	    @Column(name = "state_registered")
	    private String stateRegistered;

	  
	    @Column(name = "registration_council")
	    private String registrationCouncil;


	    @Column(name = "country_registration_with")
	    private String countryRegistrationWith; // Fixed spelling

	   
	    @Column(name = "institution_attended_for_homeopathy")
	    private String institutionAttendedForHomeopathy;

	    @Column(name = "current_job")
	    private String currentJob;

	    @Column(name = "alternate_mobile_number")
	    private String alternateMobileNumber; // Changed to String for phone number
}
