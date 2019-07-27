/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.StoreMedia;
import com.ateamforce.coffeenow.model.repository.StoreMediaRepository;
import com.ateamforce.coffeenow.service.StoreMediaService;
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
public class StoreMediaServiceImpl implements StoreMediaService {
    
    @Autowired
    StoreMediaRepository storeMediaRepository;

    @Override
    public void addStoreMedia(StoreMedia storeMedia) {
        storeMediaRepository.save(storeMedia);
    }

    @Override
    public void deleteStoreMedia(StoreMedia storeMedia) {
        storeMediaRepository.delete(storeMedia);
    }

    @Override
    public List<StoreMedia> findAllByStoreId(int storeId) {
        return storeMediaRepository.findAllByStoreId(storeId);
    }
    
}
