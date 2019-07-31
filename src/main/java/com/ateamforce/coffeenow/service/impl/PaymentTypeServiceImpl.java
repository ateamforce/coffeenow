/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.PaymentType;
import com.ateamforce.coffeenow.model.repository.PaymentTypeRepository;
import com.ateamforce.coffeenow.service.PaymentTypeService;
import com.ateamforce.coffeenow.util.ImageHandlerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
    
    @Autowired
    ImageHandlerService imageHandlerService;
    
    @Autowired
    Environment env;

    @Override
    public PaymentType addPaymentType(PaymentType paymentType) {
        
        boolean hasChanged = false;

        PaymentType persistedPaymentType = paymentTypeRepository.save(paymentType);

        if (!paymentType.getImage().isEmpty()) {
            persistedPaymentType.setHasimage(
                    imageHandlerService.saveImage(
                            env.getProperty("front.images.paymenttypes"),
                            persistedPaymentType.getId(),
                            paymentType
                    )
            );
            hasChanged = true;
        }

        return (hasChanged) ? paymentTypeRepository.save(persistedPaymentType) : persistedPaymentType;
    }

    @Override
    public void deletePaymentTypeById(int paymentTypeId) {
        boolean hasImage = paymentTypeRepository.findPaymentTypeById(paymentTypeId).isHasimage();
        paymentTypeRepository.deleteById(paymentTypeId);
        try {
            paymentTypeRepository.findPaymentTypeById(paymentTypeId);
        } catch (NullPointerException e) {
            if (hasImage) {
                imageHandlerService.deleteImage(env.getProperty("front.images.paymenttypes"), paymentTypeId);
            }
        }
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
