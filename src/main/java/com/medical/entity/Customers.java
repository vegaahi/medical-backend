package com.medical.entity;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;
import lombok.*;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "customerType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Student.class, name = "STUDENT"),
        @JsonSubTypes.Type(value = HomeopathicDoctorEntity.class, name = "HOMEOPATHICDOCTORENTITY"),
        @JsonSubTypes.Type(value = NriDoctorEntity.class, name = "NRIDOCTORENTITY")
})
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
    private String mobileNumber;
    private LocalDate dob;
    private String password;
    private String confirmPassword;
    private String permanentCountry;
    private String permanentState;
    private String permanentCity;
    private String permanentAddressLine;
    private String currentCountry;
    private String currentState;
    private String currentCity;
    private String currentAddressLine;
    private String alternatePhoneNumber;

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

    @OneToMany(mappedBy = "customers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Coins> coins;

    @OneToMany(mappedBy = "customers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Token> tokens;

    @OneToMany(mappedBy = "customerId", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Tickets> tickets;

    @OneToMany(mappedBy = "customers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Coupons> coupons;

    @Enumerated(EnumType.STRING)
    @Column(name = "customer_type", nullable = false)
    private CustomerType customerType;

    // Explicit persistence logic for `customerType`
    @PrePersist
    @PreUpdate
    private void ensureCustomerType() {
        if (this.customerType == null) {
            this.customerType = determineCustomerType();
        }
    }

    private CustomerType determineCustomerType() {
        if (this instanceof Student) return CustomerType.STUDENT;
        if (this instanceof HomeopathicDoctorEntity) return CustomerType.HOMEOPATHICDOCTORENTITY;
        if (this instanceof NriDoctorEntity) return CustomerType.NRIDOCTORENTITY;
        throw new IllegalStateException("Unknown CustomerType for class: " + this.getClass());
    }

    public enum CustomerType {
        STUDENT, HOMEOPATHICDOCTORENTITY, NRIDOCTORENTITY
    }
}
