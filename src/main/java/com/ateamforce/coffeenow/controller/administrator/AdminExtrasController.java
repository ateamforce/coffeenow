/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.controller.administrator;

import com.ateamforce.coffeenow.model.Extra;
import com.ateamforce.coffeenow.model.ExtraCategory;
import com.ateamforce.coffeenow.service.ExtraCategoryService;
import com.ateamforce.coffeenow.service.ExtraService;
import com.ateamforce.coffeenow.validator.ExtraValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author alexa
 */
@Controller
@RequestMapping("/administrator/dashboard/extras")
public class AdminExtrasController {

    @Autowired
    ExtraService extraService;

    @Autowired
    ExtraCategoryService extraCategoryService;

    @Autowired
    ExtraValidator extraValidator;

    @PostMapping
    public String admin_dashboard_extras_addExtra(
            ModelMap modelmap,
            @ModelAttribute("newExtra") @Valid Extra newExtra,
            BindingResult result
    ) throws IOException {

        if (result.hasErrors()) {

            List<Extra> extras = extraService.getAllExtras();
            for (Extra extra : extras) {
                extra.setExtracategoriesList(extraCategoryService
                        .getAllExtraCategoriesByExtraId(extra.getId()));
            }

            modelmap.addAttribute("mainFormHasErrors", true);
            modelmap.addAttribute("extracategories", extraCategoryService.getAllExtraCategories());
            modelmap.addAttribute("extras", extras);
            modelmap.addAttribute("extrasIsActive", "active");
            return "back_admin/dashboard/extras";
        }

        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Attempting to bind disallowed fields: "
                    + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }

        extraService.addExtra(newExtra);

        return "redirect:/administrator/dashboard/extras";
    }

    @GetMapping("/delete/{extraId}")
    public String admin_dashboard_extraCategories_deleteExtra(@PathVariable int extraId) {
        extraService.deleteExtraById(extraId);
        return "redirect:/administrator/dashboard/extras";
    }

    // allowed fields for the new/update Product form returned fields
    @InitBinder
    public void initialiseBinder(WebDataBinder binder) {

        // adding custom spring validator AND reenabling JSR-303 validations that were
        // disabled because of spring validator
        binder.setValidator(extraValidator);

        // setting allowed fields
        binder.setAllowedFields("id", "title", "image", "extracategoriesList", "language");

        binder.registerCustomEditor(List.class, "extracategoriesList", new CustomCollectionEditor(List.class) {
            @Override
            protected ExtraCategory convertElement(Object element) {
                if (element instanceof String) {
                    return extraCategoryService.getExtraCategoryById(Integer.parseInt(element.toString()));
                }
                return null;
            }
        });

    }
}
