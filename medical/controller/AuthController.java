package com.medical.controller;

import com.medical.service.UserActivityService;
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

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserActivityService userActivityService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        try {
            // Authenticate user
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)); 

            // Load user details
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            String role = userDetails.getAuthorities().iterator().next().getAuthority();

            // Generate JWT tokens
            String accessToken = jwtUtil.generateToken(userDetails.getUsername(), role);
            String refreshToken = jwtUtil.generateRefreshToken(userDetails.getUsername());

            Cookie accessTokenCookie = new Cookie("accessToken", accessToken);
            accessTokenCookie.setHttpOnly(true);
            accessTokenCookie.setSecure(false); // Set true in production with HTTPS
            accessTokenCookie.setMaxAge(60 * 60); // 1-hour expiry
//            accessTokenCookie.setSameSite("None");
            accessTokenCookie.setPath("/");

            Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
            refreshTokenCookie.setHttpOnly(true);
            refreshTokenCookie.setSecure(false); // Set true in production with HTTPS
            refreshTokenCookie.setMaxAge(7 * 24 * 60 * 60); // 7 days expiry
//            refreshTokenCookie.setSameSite("None");
            refreshTokenCookie.setPath("/");

            response.addCookie(accessTokenCookie);
            response.addCookie(refreshTokenCookie);

            if(role.equals("ROLE_CUSTOMER")){
                userActivityService.trackUserActivity(username,role, true);

            }


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
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            String role = userDetails.getAuthorities().iterator().next().getAuthority();
            if (!jwtUtil.validateToken(refreshToken, username)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired refresh token.");
            }

            // Generate a new access token
            String newAccessToken = jwtUtil.generateToken(username, role);

            // Set the new access token in a cookie
            Cookie accessTokenCookie = new Cookie("accessToken", newAccessToken);
            accessTokenCookie.setHttpOnly(true);
            accessTokenCookie.setSecure(false); // Use true in production with HTTPS
            accessTokenCookie.setMaxAge(60 * 60); // 15 minutes expiry
//            accessTokenCookie.setSameSite("None");
            accessTokenCookie.setPath("/");

            response.addCookie(accessTokenCookie);

            // Optionally return the token in the response body as well
            return ResponseEntity.ok("refreshed");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failed to refresh token.");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        // Clear cookies
        Cookie accessTokenCookie = new Cookie("accessToken", null);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setSecure(false); // Use true in production with HTTPS
        accessTokenCookie.setMaxAge(0); // Expire immediately
//        accessTokenCookie.setSameSite("None");
        accessTokenCookie.setPath("/");

        Cookie refreshTokenCookie = new Cookie("refreshToken", null);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(false); // Use true in production with HTTPS
        refreshTokenCookie.setMaxAge(0); // Expire immediately
//        refreshTokenCookie.setSameSite("None");
        refreshTokenCookie.setPath("/");

        response.addCookie(accessTokenCookie);
        response.addCookie(refreshTokenCookie);

        return ResponseEntity.ok("Logged out successfully");
    }
    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(HttpServletRequest request) {
        String token = null;
        String username = null;
        String role = null;

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("accessToken".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token != null) {
            username = jwtUtil.extractUserName(token);
            role = jwtUtil.extractRole(token); // Extract the role from token
        }

        if (token != null && jwtUtil.validateToken(token, username)) {
            Map<String, String> response = new HashMap<>();
            response.put("username", username);
            response.put("role", role);
            return ResponseEntity.ok(response); // Return role along with username
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
    }


}