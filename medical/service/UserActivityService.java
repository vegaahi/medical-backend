package com.medical.service;
import com.medical.entity.UserActivity;
import com.medical.repository.UserActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;


@Service
public class UserActivityService {

    @Autowired
    private UserActivityRepository userActivityRepository;
    
     @Autowired
     private CoinsService coinsService;
    @Transactional
    public void trackUserActivity(String email,String role, boolean isLoginEvent) {
        if(role.equals("ROLE_ADMIN")){
            return;
        }
        LocalDate today = LocalDate.now();
        UserActivity userActivity = userActivityRepository.findByEmailAndLastLoginDate(email, today);

        Instant now = Instant.now();


        if (userActivity == null && role.equals("ROLE_CUSTOMER")) {
            // Initialize new record for today's activity
            userActivity = new UserActivity();
            userActivity.setEmail(email);
            userActivity.setLastLoginDate(today);
            userActivity.setTotalTimeSpent(0); // Initialize total time
            userActivity.setCoinsEarned(0);

            // Set `lastActivityTime` to current time if it's the first login
            userActivity.setLastActivityTime(Timestamp.from(now));
        } else {
            // If it's not the first login, check if it is a login event
            if (isLoginEvent) {
                System.out.println("Login event");
                // Update `lastActivityTime` for a subsequent login
                userActivity.setLastActivityTime(Timestamp.from(now));
            } else {
                // Calculate time since last activity (for non-login events)
                Instant lastActivityTime = userActivity.getLastActivityTime().toInstant();
                long timeSpentInMinutes = Duration.between(lastActivityTime, now).toMinutes();

                // Add time spent to totalTimeSpent
                userActivity.setTotalTimeSpent(userActivity.getTotalTimeSpent() + timeSpentInMinutes);

                // Update `lastActivityTime` on regular activity
                userActivity.setLastActivityTime(Timestamp.from(now));
               // userActivityRepository.save(userActivity);
            }
        }

        // Grant coin if total time spent >= 10 minutes
        if (userActivity.getTotalTimeSpent() >= 10 && userActivity.getCoinsEarned() == 0) {
            userActivity.setCoinsEarned(1);
            userActivityRepository.save(userActivity);
            coinsService.addCoins( email, today); 
        }
        else {
        // Save the updated user activity record
        userActivityRepository.save(userActivity);
        }
    }
}
