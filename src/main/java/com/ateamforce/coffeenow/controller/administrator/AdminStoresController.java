package com.ateamforce.coffeenow.controller.administrator;

import com.ateamforce.coffeenow.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Sakel
 */

@Controller
@RequestMapping("/administrator/dashboard/stores")
public class AdminStoresController {

    @Autowired
    StoreService storeService;

    @PostMapping
    public String admin_dashboard_stores_toggleStore(
        @RequestParam("enabled") Integer enabled, 
        @RequestParam("storeId") Integer storeId
    ){
        storeService.enableStoreById(enabled, storeId);
        return "redirect:/administrator/dashboard/stores";
    }

    @GetMapping("/delete/{storeId}")
    public String admin_dashboard_stores_deleteStore(@PathVariable int storeId) {
        storeService.deleteById(storeId);
        return "redirect:/administrator/dashboard/stores";
    }

}
