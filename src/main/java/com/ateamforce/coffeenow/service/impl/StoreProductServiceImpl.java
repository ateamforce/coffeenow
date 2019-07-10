/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.StoreProduct;
import com.ateamforce.coffeenow.model.repository.StoreProductRepository;
import com.ateamforce.coffeenow.service.StoreProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author alexa
 */
@Repository
public class StoreProductServiceImpl implements StoreProductService {
    
    @Autowired
    StoreProductRepository storeProductRepository;
    
    @Override
    public void addStoreProduct(StoreProduct storeProduct) {
        storeProductRepository.save(storeProduct);
    }
    
    @Override
    public void deleteStoreProduct(StoreProduct storeProduct) {
        storeProductRepository.delete(storeProduct);
    }
}
