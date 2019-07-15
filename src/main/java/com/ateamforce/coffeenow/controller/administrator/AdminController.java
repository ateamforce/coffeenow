/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.controller.administrator;

import com.ateamforce.coffeenow.service.AppUserService;
import com.ateamforce.coffeenow.service.ExtraCategoryService;
import com.ateamforce.coffeenow.service.ExtraService;
import com.ateamforce.coffeenow.service.ProductCategoryService;
import com.ateamforce.coffeenow.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author alexa
 */
@Controller
@RequestMapping("/administrator/dashboard")
public class AdminController {
    
    private final static Logger LOGGER = Logger.getLogger(AdminController.class);

    @Autowired
    ProductService productService;
    
    @Autowired
    ExtraService extraService;
    
    @Autowired
    AppUserService appUserService;
    
    @Autowired
    ProductCategoryService productCategoryService;
    
    @Autowired
    ExtraCategoryService extraCategoryService;
    
    // TODO : Gets what?
    // Go to administrator dashboard page
    @RequestMapping
    public String admin_dashboard(ModelMap modelmap) {
        
        // add variable to indicate active sidebar menu
        modelmap.addAttribute("dashboardIsActive", "active");
        
        return "back_admin/dashboard/index";
    }

    // Gets all products to display them in products page
    // Go to administrator products page
    @RequestMapping("/products")
    public String admin_dashboard_products(ModelMap modelmap) {
        
        // add products
        modelmap.addAttribute("products", productService.getAllProducts());
        
        // add variable to indicate active sidebar menu
        modelmap.addAttribute("productsIsActive", "active");
        
        return "back_admin/dashboard/products";
    }

    // Gets all productcaregories to display them in productcategories page
    // Go to administrator productcategories page
    @RequestMapping("/productcategories")
    public String admin_dashboard_productcategories(ModelMap modelmap) {
        
        // add product categories
        modelmap.addAttribute("productcategories", productCategoryService.getAllProductCategories());
        
        // add connected user
        LOGGER.info("");
        // Administrator admin = (Administrator)appUserService.getUserByEmail(authentication.getPrincipal().toString());
        // modelmap.addAttribute("user", admin);
        
        // add variable to indicate active sidebar menu
        modelmap.addAttribute("productcategoriesIsActive", "active");
        
        return "back_admin/dashboard/product_categories";
    }
    
    // Gets all products to display them in products page
    // Go to administrator products page
    @RequestMapping("/extras")
    public String admin_dashboard_extras(ModelMap modelmap) {
        
        // add extras
        modelmap.addAttribute("extras", extraService.getAllExtras());
        
        // add variable to indicate active sidebar menu
        modelmap.addAttribute("extrasIsActive", "active");
        
        return "back_admin/dashboard/extras";
    }

    // Gets all productcaregories to display them in productcategories page
    // Go to administrator productcategories page
    @RequestMapping("/extracategories")
    public String admin_dashboard_extrascategories(ModelMap modelmap) {
        
        // add extras categories
        modelmap.addAttribute("extracategories", extraCategoryService.getAllExtraCategories());
        
        // add variable to indicate active sidebar menu
        modelmap.addAttribute("extracategoriesIsActive", "active");
        
        return "back_admin/dashboard/extra_categories";
    }
    
    // allowed fields for form data binding (if needed), and for any other request parameter we need to pass
    @InitBinder
    public void initialiseBinder(WebDataBinder binder) {

            // adding custom spring validator AND reenabling JSR-303 validations that were
            // disabled because of spring validator
            // binder.setValidator(productValidator);

            // setting allowed fields. add more as needed
            binder.setAllowedFields("language");
    }
    
    
}
