/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.controller.administrator;

import com.ateamforce.coffeenow.editor.StringToImageEditor;
import com.ateamforce.coffeenow.model.Extra;
import com.ateamforce.coffeenow.model.ExtraCategory;
import com.ateamforce.coffeenow.model.ProductCategory;
import com.ateamforce.coffeenow.service.ExtraCategoryService;
import com.ateamforce.coffeenow.service.ExtraService;
import com.ateamforce.coffeenow.service.ProductCategoryService;
import com.ateamforce.coffeenow.validator.ExtraCategoryValidator;
import java.io.IOException;
import java.util.List;
import javax.validation.Valid;
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
@RequestMapping("/administrator/dashboard/extracategories")
public class AdminExtraCategoriesController {
    
    @Autowired
    ExtraCategoryService extraCategoryService;
    
    @Autowired
    ExtraService extraService;
    
    @Autowired
    ProductCategoryService productCategoryService;
    
    @Autowired
    ExtraCategoryValidator extraCategoryValidator;

    // INSERT/UPDATE a product category
    @PostMapping
    public String admin_dashboard_extraCategories_addExtraCategory(
            ModelMap modelmap,
            @ModelAttribute("extraCategory") @Valid ExtraCategory extraCategory,
            BindingResult result
    ) throws IOException {
        
        if (result.hasErrors()) {
            
            List<ExtraCategory> extracategories = extraCategoryService.getAllExtraCategories();
            for (ExtraCategory extracategory : extracategories) {
                extracategory.setExtrasList(extraService.getAllExtrasByExtraCategoryId(extracategory.getId()));
                extracategory.setProductcategoriesList(productCategoryService.getAllProductCategoriesByExtraCategoryId(extracategory.getId()));
            }
            
            modelmap.addAttribute("mainFormHasErrors", true);
            modelmap.addAttribute("extracategories", extracategories);
            modelmap.addAttribute("extras", extraService.getAllExtras());
            modelmap.addAttribute("productcategories", productCategoryService.getAllProductCategories());
            modelmap.addAttribute("extracategoriesIsActive", "active");
            return "back_admin/dashboard/extra_categories";
        }
        
        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Attempting to bind disallowed fields: "
                    + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }
        
        extraCategoryService.addExtraCategory(extraCategory);
        
        return "redirect:/administrator/dashboard/extracategories";
    }

    // DELETE a product category by id
    @GetMapping("/delete/{extraCategoryId}")
    public String admin_dashboard_productCategories_deleteProductCategory(@PathVariable int extraCategoryId) {
        extraCategoryService.deleteExtraCategoryById(extraCategoryId);
        return "redirect:/administrator/dashboard/extracategories";
    }

    // allowed fields for the new/update ProductCategory form returned fields
    @InitBinder
    public void initialiseBinder(WebDataBinder binder) {

        // adding custom spring validator AND reenabling JSR-303 validations that were
        // disabled because of spring validator
        binder.setValidator(extraCategoryValidator);

        // setting allowed fields
        binder.setAllowedFields("id", "title", "parent", "image", "productcategoriesList", "extrasList", "language");
        
        binder.registerCustomEditor(List.class, "extrasList", new CustomCollectionEditor(List.class) {
            @Override
            protected Extra convertElement(Object element) {
                if (element instanceof String) {
                    return extraService.getExtraById(Integer.parseInt(element.toString()));
                }
                return null;
            }
        });
        
        binder.registerCustomEditor(List.class, "productcategoriesList", new CustomCollectionEditor(List.class) {
            @Override
            protected ProductCategory convertElement(Object element) {
                if (element instanceof String) {
                    return productCategoryService.getProductCategoryById(Integer.parseInt(element.toString()));
                }
                return null;
            }
        });
        
        binder.registerCustomEditor(MultipartFile.class, "image", new StringToImageEditor());
        
    }
    
}
