/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.controller.store;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author alexa
 */
@Controller
@RequestMapping("/store/dashboard")
public class StoreController {

    // DASHBOARD
    @RequestMapping
    public String store_dashboard(ModelMap modelmap, @ModelAttribute("mainMessage") final String msg) {
        
        // catch the flash attribute that may be coming from AccountController
        if (msg != null && !msg.isEmpty()) modelmap.addAttribute("mainMessage", msg);
        
        // add variable to indicate active sidebar menu
        modelmap.addAttribute("dashboardIsActive", "active");
        
        return "back_store/dashboard/index";
    }
    
}
