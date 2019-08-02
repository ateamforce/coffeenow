/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.controller.administrator;

import com.ateamforce.coffeenow.editor.StringToImageEditor;
import com.ateamforce.coffeenow.model.ExtraCategory;
import com.ateamforce.coffeenow.model.Product;
import com.ateamforce.coffeenow.model.ProductCategory;
import com.ateamforce.coffeenow.service.ExtraCategoryService;
import com.ateamforce.coffeenow.service.ProductCategoryService;
import com.ateamforce.coffeenow.service.ProductService;
import com.ateamforce.coffeenow.validator.ProductCategoryValidator;
import java.io.IOException;
import java.util.List;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author alexa
 */
@Controller
@RequestMapping("/administrator/dashboard/productcategories")
public class AdminProductCategoriesController {
    
    private final static Logger LOGGER = Logger.getLogger(AdminProductCategoriesController.class);

    @Autowired
    ProductCategoryService productCategoryService;
    
    @Autowired
    ProductService productService;
    
    @Autowired
    ExtraCategoryService extraCategoryService;
    
    
    @Autowired
    ProductCategoryValidator productCategoryValidator;

    // INSERT/UPDATE a product category
    @PostMapping
    public String admin_dashboard_productCategories_addProductCategory(
            ModelMap modelmap, 
            @ModelAttribute("productCategory") @Valid ProductCategory productCategory, 
            BindingResult result
    ) throws IOException {
        
        if (result.hasErrors()) {
            
            List<ProductCategory> productcategories = productCategoryService.getAllProductCategories();
            for (ProductCategory productcategory : productcategories) {
                productcategory.setProductsList(productService.getAllProductsByProductCategoryId(productcategory.getId()));
                productcategory.setExtrascategoriesList(extraCategoryService.getAllExtraCategoriesByProductCategoryId(productcategory.getId()));
            }
            
            modelmap.addAttribute("mainFormHasErrors", true);
            modelmap.addAttribute("productcategories", productcategories);
            modelmap.addAttribute("products", productService.getAllProducts());
            modelmap.addAttribute("extracategories", extraCategoryService.getAllExtraCategories());
            modelmap.addAttribute("productcategoriesIsActive", "active");
            return "back_admin/dashboard/product_categories";
        }

        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Attempting to bind disallowed fields: "
                    + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }

        productCategoryService.addProductCategory(productCategory);

        return "redirect:/administrator/dashboard/productcategories";
    }

    // DELETE a product category by id
    @GetMapping("/delete/{productCategoryId}")
    public String admin_dashboard_productCategories_deleteProductCategory(@PathVariable int productCategoryId) {
        productCategoryService.deleteProductCategoryById(productCategoryId);
        return "redirect:/administrator/dashboard/productcategories";
    }
    
    // allowed fields for the new/update ProductCategory form returned fields
    @InitBinder
    public void initialiseBinder(WebDataBinder binder) {

        // adding custom spring validator AND reenabling JSR-303 validations that were
        // disabled because of spring validator
        binder.setValidator(productCategoryValidator);

        // setting allowed fields
        binder.setAllowedFields("id", "title", "parent", "image", "extrascategoriesList", "productsList", "language");

        binder.registerCustomEditor(List.class,"productsList", new CustomCollectionEditor(List.class){
            @Override
            protected Product convertElement(Object element){
                if (element instanceof String) {
                    return productService.getProductById(Integer.parseInt(element.toString()));
                }
                return null;
            }
        });

        binder.registerCustomEditor(List.class,"extrascategoriesList", new CustomCollectionEditor(List.class){
            @Override
            protected ExtraCategory convertElement(Object element){
                if (element instanceof String) {
                    return extraCategoryService.getExtraCategoryById(Integer.parseInt(element.toString()));
                }
                return null;
            }
        });
        
        binder.registerCustomEditor(MultipartFile.class, "image", new StringToImageEditor());
            
    }

}
