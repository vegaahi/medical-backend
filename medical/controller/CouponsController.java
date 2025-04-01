package com.medical.controller;

import com.medical.entity.Coupons;
import com.medical.service.CouponsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/coupons")
public class CouponsController {

    private final CouponsService couponsService;

    @Autowired
    public CouponsController(CouponsService couponsService) {
        this.couponsService = couponsService;
    }

    @GetMapping
    public List<Coupons> getAllCoupons() {
        return couponsService.getAllCoupons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coupons> getCouponById(@PathVariable Long id) {
        return couponsService.getCouponById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{customerId}")
    public List<Coupons> getCouponsByCustomerId(@PathVariable Long customerId) {
        return couponsService.getCouponsByCustomerId(customerId);
    }

    @PostMapping
    public Coupons createCoupon(@RequestBody Coupons coupon) {
        return couponsService.saveCoupon(coupon);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable Long id) {
        couponsService.deleteCoupon(id);
        return ResponseEntity.noContent().build();
    }
}
