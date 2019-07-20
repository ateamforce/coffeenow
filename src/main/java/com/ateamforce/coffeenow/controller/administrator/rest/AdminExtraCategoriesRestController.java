/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.controller.administrator.rest;

import com.ateamforce.coffeenow.dto.ExtraCategoryDto;
import com.ateamforce.coffeenow.dto.service.ExtraCategoryDtoService;
import java.util.ArrayList;
import java.util.List;
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
    ExtraCategoryDtoService extraCategoryDtoService;

    // return a json object containing all the extra categories
    @GetMapping("/administrator/dashboard/extracategories/get")
    public List<ExtraCategoryDto> admin_getAllExtraCategories_as_json() {
        return new ArrayList(extraCategoryDtoService.getAllExtraCategories());

    }
    
    // return a json object containing a single extra category
    @GetMapping("/administrator/dashboard/extracategories/get/{extraCategoryId}")
    public ExtraCategoryDto admin_getOneExtraCategoryById_as_json(@PathVariable int extraCategoryId) {
        return extraCategoryDtoService.getExtraCategoryById(extraCategoryId);
    }

}
