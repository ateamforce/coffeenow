package com.ateamforce.coffeenow.controller.administrator.rest;

import com.ateamforce.coffeenow.model.ProductCategory;
import com.ateamforce.coffeenow.service.ExtraCategoryService;
import com.ateamforce.coffeenow.service.ProductCategoryService;
import com.ateamforce.coffeenow.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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
    
    @Autowired
    ExtraCategoryService extraCategoryService;
    
    @Autowired
    ProductService productService;
    
    // return a json object containing a single product category with products and extra categories
    @PostMapping(path = "/administrator/dashboard/productcategories/{productCategoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductCategory admin_getOneProductGategoryById_as_json(@PathVariable int productCategoryId) {
        ProductCategory productCategory=productCategoryService.getProductCategoryById(productCategoryId);
        productCategory.setExtrascategoriesList(extraCategoryService.getAllExtraCategoriesByProductCategoryId(productCategoryId));
        productCategory.setProductsList(productService.getAllProductsByProductCategoryId(productCategoryId));
        return productCategory;
    }

}
