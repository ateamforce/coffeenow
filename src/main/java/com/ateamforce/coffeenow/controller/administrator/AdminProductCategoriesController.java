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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author alexa
 */
@Controller
@RequestMapping("/administrator/dashboard/productcategories")
public class AdminProductCategoriesController {

    @Autowired
    ProductCategoryService productCategoryService;
    
    private final String redirectToClassMapping = "redirect:/administrator/dashboard/productcategories";

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String admin_dashboard_productCategories_addProductCategory(
            @ModelAttribute("newProductCategory") @Valid ProductCategory newProductCategory, 
            BindingResult result, 
            RedirectAttributes redirectAttrs
    ) throws IOException {
        
        // TODO: fix this to an appropriate solution that passes the errors without duplicating the admin_dashboard_productcategories method of AdminController
        if (result.hasErrors()) {
            redirectAttrs.addFlashAttribute("hasErrors", true); // custom attribute to indicate that there are errors (some forms are hidden initially, unless there are errors)
            redirectAttrs.addFlashAttribute("org.springframework.validation.BindingResult.newProductCategory", result);
            redirectAttrs.addFlashAttribute("newProductCategory", newProductCategory);
            return redirectToClassMapping;
        }

        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Attempting to bind disallowed fields: "
                    + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }

        productCategoryService.addProductCategory(newProductCategory);

        return redirectToClassMapping;
    }

    @GetMapping("/delete/{productCategoryId}")
    public String admin_dashboard_productCategories_deleteProductCategory(@PathVariable int productCategoryId) {
        productCategoryService.deleteProductCategoryById(productCategoryId);
        return redirectToClassMapping;
    }

    @PostMapping("/update")
    public String admin_dashboard_productCategories_updateProductCategory(@RequestParam("updatedProductCategoryJson") String updatedProductCategoryJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ProductCategory updatedProductCategory = mapper.readValue(updatedProductCategoryJson, ProductCategory.class);
        productCategoryService.updateProductCategory(updatedProductCategory);
        return redirectToClassMapping;
    }

}
