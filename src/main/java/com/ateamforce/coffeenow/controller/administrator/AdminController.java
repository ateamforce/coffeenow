/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.controller.administrator;

import com.ateamforce.coffeenow.service.ProductCategoryService;
import com.ateamforce.coffeenow.service.ProductService;
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
public class AdminController {

    @Autowired
    ProductService productService;
    
    @Autowired
    ProductCategoryService productCategoryService;
    
    // TODO : Gets what?
    // Go to administrator dashboard page
    @RequestMapping
    public String admin_dashboard() {
        return "back_admin/dashboard/index";
    }

    //Gets all products to display them in products page
    //Go to administrator products page
    @RequestMapping("/products")
    public String admin_dashboard_products(ModelMap modelmap) {
        modelmap.addAttribute("products", productService.getAllProducts());
        return "back_admin/dashboard/products";
    }

    //Gets all productcaregories to display them in productcategories page
    //Go to administrator productcategories page
    @RequestMapping("/productcategories")
    public String admin_dashboard_productcategories(ModelMap modelmap) {
        modelmap.addAttribute("productcategories", productCategoryService.getAllProductCategories());
        return "back_admin/dashboard/product_categories";
    }
    
    
}
