/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.controller.administrator;

import com.ateamforce.coffeenow.model.ExtraCategory;
import com.ateamforce.coffeenow.service.ExtraCategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author alexa
 */
@Controller
@RequestMapping("/administrator/dashboard/extracategories")
public class AdminExtraCategoriesController {

    @Autowired
    ExtraCategoryService extraCategoryService;

    @PostMapping("/add")
    public String admin_dashboard_extraCategories_addExtraCategory(@RequestParam("newExtraCategoryJson") String newExtraCategoryJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ExtraCategory newExtraCategory = mapper.readValue(newExtraCategoryJson, ExtraCategory.class);
        extraCategoryService.addExtraCategory(newExtraCategory);
        return "redirect:/administrator/dashboard/extracategories";
    }

    @GetMapping("/delete/{extraCategoryId}")
    public String admin_dashboard_extraCategories_deleteExtraCategory(@PathVariable int extraCategoryId) {
        extraCategoryService.deleteExtraCategoryById(extraCategoryId);
        return "redirect:/administrator/dashboard/extracategories";
    }

}
