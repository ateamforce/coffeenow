/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.controller.administrator.rest;

import com.ateamforce.coffeenow.model.Extra;
import com.ateamforce.coffeenow.model.ExtraCategory;
import com.ateamforce.coffeenow.service.ExtraCategoryService;
import com.ateamforce.coffeenow.service.ExtraService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author alexa
 */
@RestController
@RequestMapping("/administrator/dashboard/extracategories_extras")
public class AdminExtraCategoriesExtrasRestController {

    @Autowired
    ExtraCategoryService extraCategoryService;

    @Autowired
    ExtraService extraService;

    // get all extras of this category
    @GetMapping("/extras/{extraCategoryId}")
    public Map<String, List<Extra>> admin_dashboard_extraCategory_getextras(@PathVariable int extraCategoryId) {
        List<Extra> belongingExtras = new ArrayList(extraCategoryService
                .getExtraCategoryById(extraCategoryId)
                .getExtrasList());
        List<Extra> notBelongingExtras = new ArrayList(extraService
                .getRemainigExtrasByExtraCategoryId(extraCategoryId));
        Map<String, List<Extra>> extras = new HashMap();
        extras.put("belongingExtras", belongingExtras);
        extras.put("notBelongingExtras", notBelongingExtras);
        return extras;
    }

    // update all extras of this category (remove all existing, add all new ones)
    @PostMapping("/extras/update/{extraCategoryId}")
    public void admin_dashboard_extraCategory_removeaddextras(@PathVariable int extraCategoryId, @RequestBody List<Extra> extrasToAdd) {
        ExtraCategory extraCategory = extraCategoryService.getExtraCategoryById(extraCategoryId);
        extraCategory.getExtrasList().clear();
        extraCategory.getExtrasList().addAll(extrasToAdd);
    }

    // get all categories of this extra
    @GetMapping("/extracategories/{extraId}")
    public Map<String, List<ExtraCategory>> admin_dashboard_extra_getextraCategories(@PathVariable int extraId) {
        List<ExtraCategory> belongingExtraCategories = new ArrayList(extraService
                .getExtraById(extraId)
                .getExtraCategoriesList());
        List<ExtraCategory> notBelongingExtraCategories = new ArrayList(extraCategoryService
                .getRemainigExtraCategoriesByExtraId(extraId));
        Map<String, List<ExtraCategory>> extracategories = new HashMap();
        extracategories.put("belongingExtraCategories", belongingExtraCategories);
        extracategories.put("notBelongingExtraCategories", notBelongingExtraCategories);
        return extracategories;
    }
    
    //  update all extracategories of this extra (remove all existing, add all new ones)
    @PostMapping("/extracategories/update/{extraId}")
    public void admin_dashboard_product_removeaddextraCategories(@PathVariable int extraId, @RequestBody List<ExtraCategory> categoriesToAdd) {
        Extra extra = extraService.getExtraById(extraId);
        extra.getExtraCategoriesList().clear();
        extra.getExtraCategoriesList().addAll(categoriesToAdd);
    }
    
    
}
