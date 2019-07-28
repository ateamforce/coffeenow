package com.ateamforce.coffeenow.controller.administrator.rest;

import com.ateamforce.coffeenow.model.Product;
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
 * @author Sakel
 */
@RestController
public class AdminProductsRestController {
    
    @Autowired
    ProductCategoryService productCategoryService;
    
    @Autowired
    ProductService productService;
    
    // return a json object containing a single product with product categories
    @PostMapping(path = "/administrator/dashboard/products/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product admin_getOneProductById_as_json(@PathVariable int productId) {
        Product product = productService.getProductById(productId);
        product.setProductcategoriesList(productCategoryService.getAllProductCategoriesByProductId(productId));
        return product;
    }

}
