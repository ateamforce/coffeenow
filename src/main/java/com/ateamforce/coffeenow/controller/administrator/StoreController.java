/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.controller.administrator;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author alexa
 */
@Controller
@RequestMapping("/store/dashboard")
public class StoreController {

    // Go to store dashboard page
    @RequestMapping
    public String admin_dashboard(ModelMap modelmap) {
        

        
        return "back_store/dashboard/index";
    }
    
}
