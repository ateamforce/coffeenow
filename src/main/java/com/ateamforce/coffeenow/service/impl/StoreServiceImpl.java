package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.Store;
import com.ateamforce.coffeenow.model.repository.StoreRepository;
import com.ateamforce.coffeenow.service.StoreService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Sakel
 */
@Service
@Transactional
public class StoreServiceImpl implements StoreService {
    
    @Autowired
    StoreRepository storeRepository;

    @Override
    public Store findById(int id) {
        return storeRepository.findById(id);
    }

    @Override
    public List<Store> findAll() {
        return storeRepository.findAll();
    }

}
