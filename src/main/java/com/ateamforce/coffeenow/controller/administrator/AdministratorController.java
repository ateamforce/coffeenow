/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.controller.administrator;

import com.ateamforce.coffeenow.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author alexa
 */
@Controller
@RequestMapping("/administrator/dashboard")
public class AdministratorController {

    @Autowired
    ProductServiceImpl productserviceimpl;

    //Gets all products to display them in products page
    //Go to administrator products page
    @RequestMapping("/products")
    public String admin_dashboard_products(ModelMap modelmap) {
        modelmap.addAttribute("products", productserviceimpl.getAllProducts());
        return "back_admin/dashboard/products/products";
    }

    //Gets all productcaregories to display them in productcategories page
    //Go to administrator productcategories page
    @RequestMapping("/productcategories")
    public String admin_dashboard_productcategories(ModelMap modelmap) {
        modelmap.addAttribute("productcategories", productserviceimpl.getAllProducts());
        return "back_admin/dashboard/product_categories/productcategories";
    }
    
    
}
