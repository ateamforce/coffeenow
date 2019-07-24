package com.ateamforce.coffeenow.controller.administrator.rest;

import com.ateamforce.coffeenow.model.ExtraCategory;
import com.ateamforce.coffeenow.service.ExtraCategoryService;
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
    ExtraCategoryService extraCategoryService;
    
    // return a json object containing a single extra category
    @GetMapping("/administrator/dashboard/extracategories/{extraCategoryId}")
    public ExtraCategory admin_getOneExtraCategoryById_as_json(@PathVariable int extraCategoryId) {
        return extraCategoryService.getExtraCategoryById(extraCategoryId);
    }

}
