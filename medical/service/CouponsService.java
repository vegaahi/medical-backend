package com.medical.service;

import com.medical.entity.Coupons;
import com.medical.repository.CouponsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CouponsService {

    private final CouponsRepository couponsRepository;

    @Autowired
    public CouponsService(CouponsRepository couponsRepository) {
        this.couponsRepository = couponsRepository;
    }

    public List<Coupons> getAllCoupons() {
        return couponsRepository.findAll();
    }

    public Optional<Coupons> getCouponById(Long id) {
        return couponsRepository.findById(id);
    }

    public List<Coupons> getCouponsByCustomerId(Long customerId) {
        return couponsRepository.findByCustomersId(customerId);
    }

    public Coupons saveCoupon(Coupons coupon) {
        return couponsRepository.save(coupon);
    }

    public void deleteCoupon(Long id) {
        couponsRepository.deleteById(id);
    }
}
