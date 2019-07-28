/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service;

import com.ateamforce.coffeenow.model.StoreExtra;
import java.util.List;

/**
 *
 * @author alexa
 */
public interface StoreExtraService {
    
    void addStoreExtra(StoreExtra storeExtra);
    
    void deleteStoreExtra(StoreExtra storeExtra);
    
    List<StoreExtra> findByStoreid(int storeid);
}
