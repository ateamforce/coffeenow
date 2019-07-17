/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model.repository;

import com.ateamforce.coffeenow.model.PaymentType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author alexa
 */
@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentType, Integer> {

    PaymentType findPaymentTypeById(int paymentTypeId);

    List<PaymentType> findAllPaymentTypes();

}
