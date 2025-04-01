package com.medical.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medical.entity.PackageList;
import com.medical.service.PackageListService;

@RestController
@CrossOrigin
@RequestMapping()
public class PackageListController {

    @Autowired
    private PackageListService packageListService;

    @GetMapping("/admins/packagelist/packages")
    public ResponseEntity<List<PackageList>> getAllPackageList() {
        List<PackageList> packageList = packageListService.getAllPackageLists();
        return new ResponseEntity<>(packageList, HttpStatus.OK);
    }
    
    // Get a package by ID
    @GetMapping("/admins/packagelist/get/{id}")
    public ResponseEntity<PackageList> getPackageListById(@PathVariable Long id) {
        PackageList packageList = packageListService.getPackageListById(id);
        return new ResponseEntity<>(packageList, HttpStatus.OK);
    }

    // Create a new package
    @PostMapping("/admins/packagelist/post")
    public ResponseEntity<PackageList> createPackageList(@RequestBody PackageList packageList) {
        PackageList createdPackage = packageListService.createPackageList(packageList);
        return new ResponseEntity<>(createdPackage, HttpStatus.CREATED);
    }

    // Update an existing package by ID
    @PutMapping("/admins/packagelist/put/{id}")
    public ResponseEntity<PackageList> updatePackageListById(
            @PathVariable Long id, @RequestBody PackageList packageList) {
        PackageList updatedPackage = packageListService.updatePackageListById(id, packageList);
        return new ResponseEntity<>(updatedPackage, HttpStatus.OK);
    }

    // Delete a package by ID
    @DeleteMapping("/admins/packagelist/delete/{id}")
    public ResponseEntity<Void> deletePackageListById(@PathVariable Long id) {
        packageListService.deletePackageList(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
