package com.medical.entity;


import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student extends Customers {


private String universityName;
private int currentYear;



}
