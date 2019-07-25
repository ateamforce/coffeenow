package com.ateamforce.coffeenow.controller.administrator.rest;

import com.ateamforce.coffeenow.model.ProductCategory;
import com.ateamforce.coffeenow.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    @PostMapping(path = "/administrator/dashboard/productcategories/{productCategoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductCategory admin_getOneProductGategoryById_as_json(@PathVariable int productCategoryId) {
        return productCategoryService.getProductCategoryById(productCategoryId);
    }

}
