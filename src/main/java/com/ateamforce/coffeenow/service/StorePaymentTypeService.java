package com.ateamforce.coffeenow.service;

import com.ateamforce.coffeenow.model.StorePaymenttype;
import java.util.List;

/**
 *
 * @author Sakel
 */
public interface StorePaymentTypeService {
    
    List<StorePaymenttype> findAllByStoreId(int storeid);

}
