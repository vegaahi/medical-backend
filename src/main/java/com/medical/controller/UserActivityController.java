package com.medical.controller;

import com.medical.service.UserActivityService;
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

    @PostMapping("/customers/useractivity")
    public void trackUserActivity() {
        System.out.println("tracking user activity");
    }

}
