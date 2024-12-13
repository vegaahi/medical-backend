package com.medical.repository;

import com.medical.entity.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface UserActivityRepository  extends JpaRepository<UserActivity, Long> {

    UserActivity findByEmailAndLastLoginDate(String email, LocalDate today);
}
