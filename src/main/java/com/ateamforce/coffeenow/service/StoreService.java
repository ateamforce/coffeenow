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

}
