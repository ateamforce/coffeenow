/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.AppOrder;
import com.ateamforce.coffeenow.model.repository.AppOrderRepository;
import com.ateamforce.coffeenow.service.AppOrderService;
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
public class AppOrderServiceImpl implements AppOrderService {

    @Autowired
    AppOrderRepository appOrderRepository;

    @Override
    public void addAppOrder(AppOrder appOrder) {
        appOrderRepository.save(appOrder);
    }

    @Override
    public void deleteAppOrder(AppOrder appOrder) {
        appOrderRepository.delete(appOrder);
    }

    @Override
    public AppOrder findById(int id) {
        return appOrderRepository.findById(id);
    }

    @Override
    public List<AppOrder> findByStoreId(int storeid) {
        return appOrderRepository.findByStore(storeid);
    }

    @Override
    public List<AppOrder> findByClientId(int clientid) {
        return appOrderRepository.findByClient(clientid);
    }
    
}
