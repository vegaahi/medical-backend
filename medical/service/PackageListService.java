package com.medical.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.entity.PackageList;
import com.medical.repository.PackageListRepository;

@Service
public class PackageListService {

    @Autowired
    private PackageListRepository packageListRepository;

    // Create a new package
    public PackageList createPackageList(PackageList packageList) {
    	packageList.setCreatedAt(LocalDateTime.now());
        return packageListRepository.save(packageList);
    }

    // Get all packages
    public List<PackageList> getAllPackageLists() {
        return packageListRepository.findAll();
    }

    // Get a package by ID
    public PackageList getPackageListById(Long id) {
        return packageListRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("PackageList not found with id " + id));
    }

    // Update a package by ID
    public PackageList updatePackageListById(Long id, PackageList packageList) {
        return packageListRepository.findById(id)
            .map(existingPackage -> {
                existingPackage.setPackageName(packageList.getPackageName());
                existingPackage.setPackageType(packageList.getPackageType());
                existingPackage.setAmount(packageList.getAmount());
                existingPackage.setTransactionFee(packageList.getTransactionFee());
                existingPackage.setTotalPackageAmount(packageList.getTotalPackageAmount());
                existingPackage.setValidity(packageList.getValidity());
                existingPackage.setCreatedAt(packageList.getCreatedAt());
                return packageListRepository.save(existingPackage);
            })
            .orElseThrow(() -> new RuntimeException("Package not found with id " + id));
    }

    // Delete a package by ID
    public void deletePackageList(Long id) {
        PackageList packageList = packageListRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Package not found with id " + id));
        packageListRepository.delete(packageList);
    }
}
