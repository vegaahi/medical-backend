package com.medical.config;

import com.medical.entity.Customers;
import com.medical.entity.Admin;
import com.medical.repository.CustomerRepository;
import com.medical.repository.AdminRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SpringConfig {

    private final CustomerRepository customersRepository;
    private final AdminRepository adminRepository;

    public SpringConfig(CustomerRepository customersRepository, AdminRepository adminRepository) {
        this.customersRepository = customersRepository;
        this.adminRepository = adminRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            // First, check if the user is an admin
            Admin admin = adminRepository.findByUsername(username).orElse(null);
            if (admin != null) {
                return org.springframework.security.core.userdetails.User
                        .withUsername(admin.getUsername())
                        .password(admin.getPassword())
                        .roles("ADMIN")
                        .build();
            }

            // If not admin, check if the user is a customer
            Customers customer = customersRepository.findByEmail(username).orElseThrow(
                    () -> new UsernameNotFoundException("User not found")
            );
            return org.springframework.security.core.userdetails.User
                    .withUsername(customer.getEmail())
                    .password(customer.getPassword())
                    .roles("CUSTOMER")
                    .build();
        };
    }

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/admins/**").hasRole("ADMIN") // Only ADMIN can access /admins/**
            .requestMatchers("/customers/**").hasRole("CUSTOMER") // Only CUSTOMER can access /customers/**
            .requestMatchers("/api/**").permitAll() // Allow all requests to /api/Student
            .anyRequest().authenticated() // All other requests require authentication
        )
        .httpBasic(withDefaults()) // Basic authentication
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Stateless session
    return http.build();
}

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder()); // Password encoder
        provider.setUserDetailsService(userDetailsService()); // Custom user details service
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*")); // Allow all origins
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")); // Allow all HTTP methods
        configuration.setAllowedHeaders(List.of("*")); // Allow all headers
        configuration.setAllowCredentials(true); // Allow credentials

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Apply to all paths
        return source;
    }
}
