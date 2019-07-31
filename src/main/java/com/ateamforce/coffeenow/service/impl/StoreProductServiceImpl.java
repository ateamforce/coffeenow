/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.StoreProduct;
import com.ateamforce.coffeenow.model.repository.StoreProductRepository;
import com.ateamforce.coffeenow.service.StoreProductService;
import com.ateamforce.coffeenow.util.ImageHandlerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alexa
 */
@Repository
@Transactional
public class StoreProductServiceImpl implements StoreProductService {
    
    @Autowired
    StoreProductRepository storeProductRepository;
    
    @Autowired
    ImageHandlerService imageHandlerService;
    
    @Autowired
    Environment env;
    
    @Override
    public StoreProduct addStoreProduct(StoreProduct storeProduct) {
        
        boolean hasChanged = false;

        StoreProduct persistedStoreProduct = storeProductRepository.save(storeProduct);

        if (!storeProduct.getImage().isEmpty()) {
            persistedStoreProduct.setHasimage(
                    imageHandlerService.saveImage(
                            env.getProperty("front.images.stores") + "products/" + persistedStoreProduct.getStoreProductPK().getStoreid() + "/",
                            persistedStoreProduct.getStoreProductPK().getProductid(),
                            storeProduct
                    )
            );
            hasChanged = true;
        }

        return (hasChanged) ? storeProductRepository.save(persistedStoreProduct) : persistedStoreProduct;
    }
    
    @Override
    public void deleteStoreProduct(StoreProduct storeProduct) {
        boolean hasImage = storeProductRepository.findById(storeProduct.getStoreProductPK()).get().isHasimage();
        storeProductRepository.deleteById(storeProduct.getStoreProductPK());
        try {
            storeProductRepository.findById(storeProduct.getStoreProductPK());
        } catch (NullPointerException e) {
            if (hasImage) {
                imageHandlerService.deleteImage(
                        env.getProperty("front.images.stores") + "products/" + 
                                + storeProduct.getStoreProductPK().getStoreid() 
                                + "/", 
                        storeProduct.getStoreProductPK().getProductid()
                );
            }
        }
    }

    @Override
    public List<StoreProduct> findAllByStoreId(int storeId) {
        return storeProductRepository.findByStoreid(storeId);
    }
}
