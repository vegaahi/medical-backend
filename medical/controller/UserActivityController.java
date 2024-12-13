package com.medical.controller;

import com.medical.service.UserActivityService;
import com.medical.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserActivityController {

    @Autowired
    UserActivityService userActivityService;
    @Autowired
    private JwtUtil jwtUtil;
    
    @PostMapping("/customers/useractivity")
    public void trackUserActivity(HttpServletRequest request) {
        String token = null;
        String username = null;
        String role = null;

        // Extract token from cookies
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("accessToken".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        try {
            // Extract username if token is present
            if (token != null) {
                username = jwtUtil.extractUserName(token);
                role = jwtUtil.extractRole(token);
            }
        System.out.println("tracking user activity");
        userActivityService.trackUserActivity(username, role,false);
    } catch (Exception e) {
        // Handle expired token
    	e.getMessage();
      
}
    }
}