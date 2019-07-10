/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.StoreExtra;
import com.ateamforce.coffeenow.model.repository.StoreExtraRepository;
import com.ateamforce.coffeenow.service.StoreExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexa
 */
@Service
public class StoreExtraServiceImpl implements StoreExtraService {
    
    @Autowired
    StoreExtraRepository storeExtraRepository;

    @Override
    public void addStoreExtra(StoreExtra storeExtra) {
        storeExtraRepository.save(storeExtra);
    }

    @Override
    public void deleteStoreExtra(StoreExtra storeExtra) {
        storeExtraRepository.delete(storeExtra);
    }
    
}
