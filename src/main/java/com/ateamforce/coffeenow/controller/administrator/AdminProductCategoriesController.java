/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.controller.administrator;

import com.ateamforce.coffeenow.model.ProductCategory;
import com.ateamforce.coffeenow.service.ProductCategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author alexa
 */
@Controller
@RequestMapping("/administrator/dashboard/productcategories")
public class AdminProductCategoriesController {

    @Autowired
    ProductCategoryService productCategoryService;

    @PostMapping("/add")
    public String admin_dashboard_productCategories_addProductCategory(@RequestParam("newProductCategoryJson") String newProductCategoryJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ProductCategory newProductCategory = mapper.readValue(newProductCategoryJson, ProductCategory.class);
        productCategoryService.addProductCategory(newProductCategory);
        return "redirect:/administrator/dashboard/productcategories";
    }

    @GetMapping("/delete/{productCategoryId}")
    public String admin_dashboard_productCategories_deleteProductCategory(@PathVariable int productCategoryId) {
        productCategoryService.deleteProductCategoryById(productCategoryId);
        return "redirect:/administrator/dashboard/productcategories";
    }

    @PostMapping("/update")
    public String admin_dashboard_productCategories_updateProductCategory(@RequestParam("updatedProductCategoryJson") String updatedProductCategoryJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ProductCategory updatedProductCategory = mapper.readValue(updatedProductCategoryJson, ProductCategory.class);
        productCategoryService.updateProductCategory(updatedProductCategory);
        return "redirect:/administrator/dashboard/productcategories";
    }

}
