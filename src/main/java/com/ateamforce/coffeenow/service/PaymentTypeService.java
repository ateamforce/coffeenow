/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service;

import com.ateamforce.coffeenow.model.PaymentType;
import java.util.List;

/**
 *
 * @author alexa
 */
public interface PaymentTypeService {

    PaymentType addPaymentType(PaymentType paymentType);

    void deletePaymentTypeById(int paymentTypeId);

    void updatePaymentType(PaymentType paymentType);

    PaymentType getPaymentTypeById(int paymentTypeId);

    List<PaymentType> getAllPaymentTypes();
}
