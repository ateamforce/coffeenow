package com.ateamforce.coffeenow.controller.administrator.rest;

import com.ateamforce.coffeenow.dto.ProductCategoryDto;
import com.ateamforce.coffeenow.dto.service.ProductCategoryDtoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * This is a REST controller responsible for getting productcategories
 * as json.
 *
 *
 * @author Sakel
 */
@RestController
public class AdminProductCategoriesRestController {
    
    @Autowired
    ProductCategoryDtoService productCategoryDtoService;

    // return a json object containing all the product categories
    @GetMapping("/administrator/dashboard/productcategories/get")
    public List<ProductCategoryDto> admin_getAllProductGategories_as_json() {
        return new ArrayList(productCategoryDtoService.getAllProductCategories());
        
    }

}
