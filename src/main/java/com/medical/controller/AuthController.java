package com.medical.controller;

import com.medical.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        try {
            // Authenticate user
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            // Load user details
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);


            // Set token in an HTTP-only cookie
            // Generate JWT token
            String token = jwtUtil.generateToken(userDetails.getUsername());
            Cookie accessTokenCookie = new Cookie("accessToken", token);
            accessTokenCookie.setHttpOnly(true);
            accessTokenCookie.setSecure(true); // Use true in production with HTTPS
            accessTokenCookie.setMaxAge(15 * 60); // 15 minutes
            accessTokenCookie.setPath("/");

            response.addCookie(accessTokenCookie);



            return ResponseEntity.status(200).body(token);
        } catch (Exception e) {
            System.out.println(e);
            response.setStatus(401);
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}