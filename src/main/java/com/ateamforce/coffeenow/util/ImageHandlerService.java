package com.ateamforce.coffeenow.util;

import com.ateamforce.coffeenow.model._ImageCarrier;

/**
 *
 * @author Sakel
 */
public interface ImageHandlerService {

    boolean saveImage(String path, Integer id, _ImageCarrier businessObj);
    
}
