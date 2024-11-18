package com.medical.controller;

import com.medical.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

            // Generate JWT tokens
            String accessToken = jwtUtil.generateToken(userDetails.getUsername());
            String refreshToken = jwtUtil.generateRefreshToken(userDetails.getUsername());

            // Set access token in HTTP-only cookie
            Cookie accessTokenCookie = new Cookie("accessToken", accessToken);
            accessTokenCookie.setHttpOnly(true);
            accessTokenCookie.setSecure(true); // Set true in production with HTTPS
            accessTokenCookie.setMaxAge(15 * 60); // 15 minutes expiry
            accessTokenCookie.setPath("/");

            // Set refresh token in HTTP-only cookie
            Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
            refreshTokenCookie.setHttpOnly(true);
            refreshTokenCookie.setSecure(true); // Set true in production with HTTPS
            refreshTokenCookie.setMaxAge(7 * 24 * 60 * 60); // 7 days expiry
            refreshTokenCookie.setPath("/");

            // Add cookies to response
            response.addCookie(accessTokenCookie);
            response.addCookie(refreshTokenCookie);

//            return ResponseEntity.ok("Login successful");
            return ResponseEntity.ok(accessToken);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshAccessToken(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Extract the refresh token from cookies
            String refreshToken = null;
            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if ("refreshToken".equals(cookie.getName())) {
                        refreshToken = cookie.getValue();
                        break;
                    }
                }
            }

            if (refreshToken == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token is missing.");
            }

            // Validate the refresh token
            String username = jwtUtil.extractUserName(refreshToken);
            if (!jwtUtil.validateToken(refreshToken, username)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired refresh token.");
            }

            // Generate a new access token
            String newAccessToken = jwtUtil.generateToken(username);

            // Set the new access token in a cookie
            Cookie accessTokenCookie = new Cookie("accessToken", newAccessToken);
            accessTokenCookie.setHttpOnly(true);
            accessTokenCookie.setSecure(true); // Use true in production with HTTPS
            accessTokenCookie.setMaxAge(15 * 60); // 15 minutes expiry
            accessTokenCookie.setPath("/");

            response.addCookie(accessTokenCookie);

            // Optionally return the token in the response body as well
            return ResponseEntity.ok("refreshed");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failed to refresh token.");
        }
    }


}