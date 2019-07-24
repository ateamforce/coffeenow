package com.ateamforce.coffeenow.controller.administrator.rest;

import com.ateamforce.coffeenow.model.ProductCategory;
import com.ateamforce.coffeenow.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * BETA - This is a REST controller responsible for getting productcategories
 * as json.
 *
 *
 * @author Sakel
 */
@RestController
public class AdminProductCategoriesRestController {
    
    @Autowired
    ProductCategoryService productCategoryService;
    
    // return a json object containing a single product category
    @GetMapping("/administrator/dashboard/productcategories/{productCategoryId}")
    public ProductCategory admin_getOneProductGategoryById_as_json(@PathVariable int productCategoryId) {
        return productCategoryService.getProductCategoryById(productCategoryId);
    }

}
