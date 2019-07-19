/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.controller.administrator;

import com.ateamforce.coffeenow.model.ProductCategory;
import com.ateamforce.coffeenow.service.ProductCategoryService;
import com.ateamforce.coffeenow.util.ImageHandlerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    
    @Autowired
    ImageHandlerService imageHandlerService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String admin_dashboard_productCategories_addProductCategory(@ModelAttribute("newProductCategory") @Valid ProductCategory newProductCategory, BindingResult result, HttpServletRequest request) throws IOException {
        
        if (result.hasErrors()) return "back_admin/dashboard/product_categories";
        
        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0)
                throw new RuntimeException("Attempting to bind disallowed fields: "
                                + StringUtils.arrayToCommaDelimitedString(suppressedFields));

        ProductCategory newProductCategoryFinal = productCategoryService.addProductCategory(newProductCategory);
        imageHandlerService.saveImage("/resources/front/images/products/categories/",newProductCategoryFinal.getId() , newProductCategoryFinal);
        
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
