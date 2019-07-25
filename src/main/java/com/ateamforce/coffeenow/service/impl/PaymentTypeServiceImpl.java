/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.PaymentType;
import com.ateamforce.coffeenow.model.repository.PaymentTypeRepository;
import com.ateamforce.coffeenow.service.PaymentTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alexa
 */
@Service
@Transactional
public class PaymentTypeServiceImpl implements PaymentTypeService {

    @Autowired
    PaymentTypeRepository paymentTypeRepository;

    @Override
    public void addPaymentType(PaymentType paymentType) {
        paymentTypeRepository.save(paymentType);
    }

    @Override
    public void deletePaymentTypeById(int paymentTypeId) {
        paymentTypeRepository.deleteById(paymentTypeId);
    }

    @Override
    public PaymentType getPaymentTypeById(int paymentTypeId) {
        return paymentTypeRepository.findPaymentTypeById(paymentTypeId);
    }

    @Override
    public List<PaymentType> getAllPaymentTypes() {
        return paymentTypeRepository.findAllPaymentTypes();
    }

    @Override
    public void updatePaymentType(PaymentType paymentType) {
        paymentTypeRepository.save(paymentType);
    }
}
