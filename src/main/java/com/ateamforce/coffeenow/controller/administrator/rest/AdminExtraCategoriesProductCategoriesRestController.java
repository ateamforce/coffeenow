/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.controller.administrator.rest;

import com.ateamforce.coffeenow.dto.ExtraCategoryDto;
import com.ateamforce.coffeenow.dto.ProductCategoryDto;
import com.ateamforce.coffeenow.dto.service.ExtraCategoryDtoService;
import com.ateamforce.coffeenow.dto.service.ProductCategoryDtoService;
import com.ateamforce.coffeenow.model.ExtraCategory;
import com.ateamforce.coffeenow.model.ProductCategory;
import com.ateamforce.coffeenow.service.ExtraCategoryService;
import com.ateamforce.coffeenow.service.ProductCategoryService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author alexa
 */
@RestController
@RequestMapping("/administrator/dashboard/extras_products")
public class AdminExtraCategoriesProductCategoriesRestController {

    @Autowired
    ExtraCategoryDtoService extraCategoryDtoService;

    @Autowired
    ProductCategoryDtoService productCategoryDtoService;

    @Autowired
    ExtraCategoryService extraCategoryService;

    @Autowired
    ProductCategoryService productCategoryService;

    @GetMapping("/extracategories/{productCategoryId}")
    public Map<String, List<ExtraCategoryDto>> admin_dashboard_productCategory_getExtraCategories(@PathVariable int productCategoryId) {
        List<ExtraCategoryDto> belongingExtraCategories = new ArrayList(extraCategoryDtoService
                .getAllExtraCategoriesByProductCategoryId(productCategoryId));
        List<ExtraCategoryDto> notBelongingExtraCategories = new ArrayList(extraCategoryDtoService
                .getRemainigExtraCategoriesByProductCategoryId(productCategoryId));
        Map<String, List<ExtraCategoryDto>> extraCategories = new HashMap();
        extraCategories.put("belongingExtraCategories", belongingExtraCategories);
        extraCategories.put("notBelongingExtraCategories", notBelongingExtraCategories);
        return extraCategories;
    }

    @PostMapping("/extracategories/update/{productCategoryId}")
    public void admin_dashboard_productCategory_removeaddExtraCategories(@PathVariable int productCategoryId, @RequestBody List<ExtraCategory> extraCategoriesToAdd) {
        ProductCategory productCategory = productCategoryService.getProductCategoryById(productCategoryId);
        productCategory.getExtrascategoriesList().clear();
        productCategory.getExtrascategoriesList().addAll(extraCategoriesToAdd);
    }

    @GetMapping("/productcategories/{extraCategoryId}")
    public Map<String, List<ProductCategoryDto>> admin_dashboard_extraCategory_getProductCategories(@PathVariable int extraCategoryId) {
        List<ProductCategoryDto> belongingProductCategories = new ArrayList(productCategoryDtoService
                .getAllProductCategoriesByExtraCategoryId(extraCategoryId));
        List<ProductCategoryDto> notBelongingProductCategories = new ArrayList(productCategoryDtoService
                .getRemainigProductCategoriesByExtraCategoryId(extraCategoryId));
        Map<String, List<ProductCategoryDto>> productCategories = new HashMap();
        productCategories.put("belongingProductCategories", belongingProductCategories);
        productCategories.put("notBelongingProductCategories", notBelongingProductCategories);
        return productCategories;
    }

    @PostMapping("/productcategories/update/{extraCategoryId}")
    public void admin_dashboard_extraCategory_removeaddProductCategories(@PathVariable int extraCategoryId, @RequestBody List<ProductCategory> productCategoriesToAdd) {
        ExtraCategory extraCategory = extraCategoryService.getExtraCategoryById(extraCategoryId);
        extraCategory.getProductcategoriesList().clear();
        extraCategory.getProductcategoriesList().addAll(productCategoriesToAdd);
    }
}
