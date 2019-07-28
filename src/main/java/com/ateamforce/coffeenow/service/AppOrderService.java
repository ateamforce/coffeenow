/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service;

import com.ateamforce.coffeenow.model.AppOrder;
import java.util.List;

/**
 *
 * @author alexa
 */
public interface AppOrderService {
    
    void addAppOrder(AppOrder appOrder);
    
    void deleteAppOrder(AppOrder appOrder);
    
    AppOrder findById(int id);
    
    List<AppOrder> findByStoreId(int storeid);
    
    List<AppOrder> findByClientId(int clientid);
    
}
