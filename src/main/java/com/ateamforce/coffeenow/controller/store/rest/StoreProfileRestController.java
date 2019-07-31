package com.ateamforce.coffeenow.controller.store.rest;

import com.ateamforce.coffeenow.model.Store;
import com.ateamforce.coffeenow.model.StorePaymenttype;
import com.ateamforce.coffeenow.service.StorePaymentTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 *
 * @author Sakel
 */
@RestController
@Transactional
public class StoreProfileRestController {
    
    @Autowired
    StorePaymentTypeService storePaymentTypeService;
    
    // UPDATE STORE PAYMENT TYPES
    @PostMapping(path = "/store/dashboard/profile/paytypes", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody List<StorePaymenttype> store_dashboard_profile_updatePayTypes(
            @SessionAttribute(name = "currentUser") Store currentUser,
            @RequestBody String[] payTypeIds
    ) {
        
        storePaymentTypeService.deleteAllByStoreId(currentUser.getId());
        for( String id : payTypeIds ){
            storePaymentTypeService.addOne(currentUser.getId(), Integer.parseInt(id));
        }
        
        return storePaymentTypeService.findAllByStoreId(currentUser.getId());
    }

}
