/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service;

import com.ateamforce.coffeenow.model.StoreMedia;
import java.util.List;

/**
 *
 * @author alexa
 */
public interface StoreMediaService {

    void addStoreMedia(StoreMedia storeMedia);

    void deleteStoreMedia(StoreMedia storeMedia);
    
    List<StoreMedia> findByStore(int storeid);
}
