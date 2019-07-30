package com.ateamforce.coffeenow.service;

import com.ateamforce.coffeenow.model.Store;
import java.util.List;

/**
 *
 * @author Sakel
 */
public interface StoreService {
    
    Store findById(int id);
    
    List<Store> findAll();
    
    void deleteById(int storeId);
    
    void enableStoreById(int enabled, int storeId);

}
