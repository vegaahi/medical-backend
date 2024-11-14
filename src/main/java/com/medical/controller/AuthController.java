package com.medical.controller;

import com.medical.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        try {
            // Authenticate user
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            // Load user details
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Generate JWT token
            String token = jwtUtil.generateToken(userDetails.getUsername());

            return ResponseEntity.ok("Bearer " + token);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
