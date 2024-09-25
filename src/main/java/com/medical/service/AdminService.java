package com.medical.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.medical.entity.Admin;

import com.medical.repository.AdminRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
	   @Autowired
	    private AdminRepository adminRepository;

	    public List<Admin> getAllAdmins() {
	        return adminRepository.findAll();
	    }

	    public Optional<Admin> getAdminById(Long id) {
	        return adminRepository.findById(id);
	    }

	    public Admin createAdmin(Admin admin) {
	        return adminRepository.save(admin);
	    }

	    public Optional<Admin> updateAdmin(Long id, Admin adminDetails) {
	        return adminRepository.findById(id)
	                .map(admin -> {
	                    admin.setUsername(adminDetails.getUsername());
	                    admin.setPassword(adminDetails.getPassword());
	                    return adminRepository.save(admin);
	                });
	    }

	    public boolean deleteAdmin(Long id) {
	        return adminRepository.findById(id)
	                .map(admin -> {
	                    adminRepository.delete(admin);
	                    return true;
	                }).orElse(false);
	    }

		
		
}
