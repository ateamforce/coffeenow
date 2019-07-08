/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.OrderProduct;
import com.ateamforce.coffeenow.model.repository.OrderProductRepository;
import com.ateamforce.coffeenow.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexa
 */
@Service
public class OrderProductServiceImpementation implements OrderProductService  {
    
    @Autowired
    OrderProductRepository orderProductRepository;

    @Override
    public void addOrderProduct(OrderProduct orderProduct) {
        orderProductRepository.save(orderProduct);
    }

    @Override
    public void deleteOrderProduct(OrderProduct OrderProduct) {
        orderProductRepository.delete(OrderProduct);
    }
    
}
