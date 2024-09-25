package com.medical.entity;

import java.time.LocalDate;
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
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
private String fullName;
private String email;
private String username;
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

private String university;
private int year;

private String alternatePhoneNumber; // Phone number as String

}
