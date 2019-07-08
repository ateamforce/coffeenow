/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service;

import com.ateamforce.coffeenow.model.PaymentType;

/**
 *
 * @author alexa
 */
public interface PaymentTypeService {
    
    void addPaymentType(PaymentType paymentType);
    
    void deletePaymentType(PaymentType paymentType);
}
