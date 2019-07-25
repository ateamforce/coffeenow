/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.StoreMedia;
import com.ateamforce.coffeenow.model.repository.StoreMediaRepository;
import com.ateamforce.coffeenow.service.StoreMediaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alexa
 */
@Service
@Transactional
public class StoreMediaServiceImpl implements StoreMediaService {
    
    @Autowired
    StoreMediaRepository storeRepository;

    @Override
    public void addStoreMedia(StoreMedia storeMedia) {
        storeRepository.save(storeMedia);
    }

    @Override
    public void deleteStoreMedia(StoreMedia storeMedia) {
        storeRepository.delete(storeMedia);
    }
    
}
