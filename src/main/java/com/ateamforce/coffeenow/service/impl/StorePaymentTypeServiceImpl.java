package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.StorePaymenttype;
import com.ateamforce.coffeenow.model.repository.StorePaymentTypeRepository;
import com.ateamforce.coffeenow.service.StorePaymentTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sakel
 */
@Service
public class StorePaymentTypeServiceImpl implements StorePaymentTypeService {
    
    @Autowired
    StorePaymentTypeRepository storePaymentTypeRepository;

    @Override
    public List<StorePaymenttype> findAllByStoreId(int storeid) {
        return storePaymentTypeRepository.findByStoreid(storeid);
    }

}
