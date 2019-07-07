/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.PaymentType;
import com.ateamforce.coffeenow.model.repository.PaymentTypeRepository;
import com.ateamforce.coffeenow.service.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexa
 */
@Service
public class PaymentTypeServiceImplementation implements PaymentTypeService {
    
    @Autowired
    PaymentTypeRepository paymentTypeRepository;

    @Override
    public void addPaymentType(PaymentType paymentType) {
        paymentTypeRepository.save(paymentType);
    }

    @Override
    public void deletePaymentType(PaymentType paymentType) {
        paymentTypeRepository.delete(paymentType);
    }
}
