/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.controller.administrator.rest;

import com.ateamforce.coffeenow.model.Extra;
import com.ateamforce.coffeenow.service.ExtraCategoryService;
import com.ateamforce.coffeenow.service.ExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author alexa
 */
@RestController
public class AdminExtrasRestController {
    
    @Autowired
    ExtraCategoryService extraCategoryService;
    
    @Autowired
    ExtraService extraService;
    
    // return a json object containing a single extra with extra categories
    @PostMapping(path = "/administrator/dashboard/extras/{extraId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Extra admin_getOneExtraById_as_json(@PathVariable int extraId) {
        Extra extra = extraService.getExtraById(extraId);
        extra.setExtracategoriesList(extraCategoryService.getAllExtraCategoriesByExtraId(extraId));
        return extra;
    }
}
