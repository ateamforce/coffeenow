/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.StoreMedia;
import com.ateamforce.coffeenow.model.repository.StoreMediaRepository;
import com.ateamforce.coffeenow.service.StoreMediaService;
import com.ateamforce.coffeenow.util.ImageHandlerService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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

    @Autowired
    ImageHandlerService imageHandlerService;

    @Autowired
    Environment env;

    @Override
    public StoreMedia addStoreMedia(StoreMedia storeMedia) {

        boolean hasChanged = false;

        StoreMedia persistedStoreMedia = storeMediaRepository.save(storeMedia);

        if (!storeMedia.getImage().isEmpty()) {
            persistedStoreMedia.setHasimage(
                    imageHandlerService.saveImage(
                            env.getProperty("front.images.stores") + "media/" + persistedStoreMedia.getStoreid() + "/",
                            persistedStoreMedia.getId(),
                            storeMedia
                    )
            );
            hasChanged = true;
        }

        return (hasChanged) ? storeMediaRepository.save(persistedStoreMedia) : persistedStoreMedia;
    }

    @Override
    public void deleteStoreMedia(StoreMedia storeMedia) {
        storeMediaRepository.delete(storeMedia);
    }

    @Override
    public List<StoreMedia> findByStore(int storeid) {
        return storeMediaRepository.findByStore(storeid);
    }

    @Override
    public void deleteStoreMediaByid(int storeMediaId) {

        StoreMedia storeMedia = storeMediaRepository.findById(storeMediaId);
        boolean hasImage = storeMedia.isHasimage();
        storeMediaRepository.deleteById(storeMediaId);

        if ((storeMediaRepository.findById(storeMediaId)) == null && hasImage) {
            imageHandlerService.deleteImage(env
                    .getProperty("front.images.stores") + "media/" + storeMedia.getStoreid() + "/", storeMediaId);
        }
    }

}
