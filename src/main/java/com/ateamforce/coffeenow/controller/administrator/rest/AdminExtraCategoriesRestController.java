package com.ateamforce.coffeenow.controller.administrator.rest;

import com.ateamforce.coffeenow.dto.ExtraCategoryDto;
import com.ateamforce.coffeenow.dto.service.ExtraCategoryDtoService;
import com.ateamforce.coffeenow.dto.service.ProductCategoryDtoService;
import com.ateamforce.coffeenow.service.ExtraCategoryService;
import com.ateamforce.coffeenow.service.ExtraService;
import com.ateamforce.coffeenow.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * This is a REST controller responsible for getting extracategories as json.
 *
 * @author alexa
 */
@RestController
public class AdminExtraCategoriesRestController {
    
    @Autowired
    ExtraService extraService;
    
    @Autowired
    ExtraCategoryDtoService extraCategoryDtoService;
    
    @Autowired
    ProductCategoryDtoService productCategoryDtoService;
    
    // return a json object containing a single extra category
    @GetMapping("/administrator/dashboard/extracategories/{extraCategoryId}")
    public ExtraCategoryDto admin_getOneExtraCategoryById_as_json(@PathVariable int extraCategoryId) {
        ExtraCategoryDto extraCategory=extraCategoryDtoService.getExtraCategoryDtoById(extraCategoryId);
        extraCategory.setExtrasList(extraService.getAllExtrasByExtraCategoryId(extraCategoryId));
        extraCategory.setProductcategoriesList(productCategoryDtoService.getAllProductCategoriesByExtraCategoryId(extraCategoryId));
        return extraCategory;
    }

}
