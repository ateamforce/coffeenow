package com.ateamforce.coffeenow.controller.store;

import com.ateamforce.coffeenow.model.Store;
import com.ateamforce.coffeenow.model.StoreExtra;
import com.ateamforce.coffeenow.service.ExtraService;
import com.ateamforce.coffeenow.service.StoreExtraService;
import java.io.IOException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 *
 * @author Sakel
 */
@Controller
@RequestMapping("/store/dashboard/profile/extras")
public class StoreProfileExtrasController {
    
    @Autowired
    StoreExtraService storeExtraService;
    
    @Autowired
    ExtraService extraService;
    
    // EXTRAS
    @GetMapping
    public String store_dashboard_profile_extras(
            @SessionAttribute(name = "currentUser") Store currentUser, 
            ModelMap modelmap, 
            @ModelAttribute("storeExtra") StoreExtra storeExtra
    ) {

        // add store extras
        modelmap.addAttribute("storeExtras", storeExtraService.findByStoreid(currentUser.getId()));
        
        // add extras
        modelmap.addAttribute("extras", extraService.getAllExtras());

        // add variable to indicate active sidebar menu
        modelmap.addAttribute("extrasIsActive", "active");
        
        return "back_store/dashboard/extras";
    }
    
    @PostMapping
    public String store_dashboard_profile_extras_addStoreExtra(
            @SessionAttribute(name = "currentUser") Store currentUser, 
            ModelMap modelmap,
            @ModelAttribute("storeExtra") @Valid StoreExtra storeExtra,
            BindingResult result
    ) throws IOException {

        if (result.hasErrors()) {
            modelmap.addAttribute("storeExtras", storeExtraService.findByStoreid(currentUser.getId()));
            modelmap.addAttribute("extras", extraService.getAllExtras());
            modelmap.addAttribute("extrasIsActive", "active");
            return "back_store/dashboard/extras";
        }

        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Attempting to bind disallowed fields: "
                    + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }

        storeExtraService.addStoreExtra(storeExtra);

        return "redirect:/store/dashboard/profile/extras";
    }

}