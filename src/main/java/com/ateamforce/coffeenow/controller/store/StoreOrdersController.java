package com.ateamforce.coffeenow.controller.store;

import com.ateamforce.coffeenow.model.Store;
import com.ateamforce.coffeenow.service.AppOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 *
 * @author Sakel
 */
@Controller
@RequestMapping("/store/dashboard/orders")
public class StoreOrdersController {
    
    @Autowired
    AppOrderService orderService;
    
    // ORDERS
    @GetMapping
    public String store_dashboard_orders(
            @SessionAttribute(name = "currentUser") Store currentUser, 
            ModelMap modelmap
    ) {

        // add store extras
        modelmap.addAttribute("storeOrders", orderService.findByStoreId(currentUser.getId()));

        // add variable to indicate active sidebar menu
        modelmap.addAttribute("ordersIsActive", "active");
        
        return "back_store/dashboard/orders";
    }

}