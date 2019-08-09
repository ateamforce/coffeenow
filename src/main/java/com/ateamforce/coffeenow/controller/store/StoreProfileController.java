package com.ateamforce.coffeenow.controller.store;

import com.ateamforce.coffeenow.dto.NewStoreDto;
import com.ateamforce.coffeenow.model.Store;
import com.ateamforce.coffeenow.model.StoreMedia;
import com.ateamforce.coffeenow.service.AppUserService;
import com.ateamforce.coffeenow.service.PaymentTypeService;
import com.ateamforce.coffeenow.service.StoreMediaService;
import com.ateamforce.coffeenow.service.StorePaymentTypeService;
import java.io.IOException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 *
 * @author Sakel
 */
@Controller
@RequestMapping("/store/dashboard/profile")
public class StoreProfileController {
    
    @Autowired
    AppUserService appUserService;
    
    @Autowired
    StorePaymentTypeService storePaymentTypeService;
    
    @Autowired
    PaymentTypeService paymentTypeService;
    
    @Autowired
    StoreMediaService storeMediaService;
    
    // PROFILE page of store. Includes forms for payment types / profile details / store media
    @GetMapping
    public String store_dashboard_profile(
            @SessionAttribute(name = "currentUser") Store currentUser, 
            ModelMap modelmap, 
            @ModelAttribute("storeMedia") StoreMedia storeMedia,
            @ModelAttribute("store") NewStoreDto store, 
            @ModelAttribute("mainMessage") final String msg
    ) {
        
        if (msg != null && !msg.isEmpty()) modelmap.addAttribute("mainMessage", msg);

        // add store payment types
        modelmap.addAttribute("paytypes", storePaymentTypeService.findAllByStoreId(currentUser.getId()));
        // add all payment types
        modelmap.addAttribute("allPaytypes", paymentTypeService.getAllPaymentTypes());
        
        // add store media
        modelmap.addAttribute("allMedia", storeMediaService.findByStore(currentUser.getId()));
        
        // add variable to indicate active sidebar menu
        modelmap.addAttribute("profileIsActive", "active");
        
        return "back_store/dashboard/profile";
    }
    
    // UPDATE STORE DETAILS
    @PostMapping
    public String store_dashboard_profile_updateStore(
            @SessionAttribute(name = "currentUser") Store currentUser,
            ModelMap modelmap,
            @ModelAttribute("store") @Valid NewStoreDto store,
            BindingResult result
    ) throws IOException {

        if ( currentUser.getId().compareTo(store.getId()) != 0 ) result.rejectValue("id", "id.iswrong");
        
        if (result.hasErrors()) {
            // add store payment types
            modelmap.addAttribute("paytypes", storePaymentTypeService.findAllByStoreId(currentUser.getId()));
            // add all payment types
            modelmap.addAttribute("allPaytypes", paymentTypeService.getAllPaymentTypes());
            // add store media
            modelmap.addAttribute("allMedia", storeMediaService.findByStore(currentUser.getId()));
            // add variable to indicate active sidebar menu
            modelmap.addAttribute("profileIsActive", "active");
            
            return "back_store/dashboard/profile";
        }
        else {
            String[] suppressedFields = result.getSuppressedFields();
            if (suppressedFields.length > 0) {
                throw new RuntimeException("Attempting to bind disallowed fields: "
                        + StringUtils.arrayToCommaDelimitedString(suppressedFields));
            }

            appUserService.addAppUser(store);
        }

        return "redirect:/store/dashboard/profile";
    }
    
    // ADD STORE MEDIA
    @RequestMapping(value = "/media", method = RequestMethod.POST)
    public String store_dashboard_profile_addMedia(
            @SessionAttribute(name = "currentUser") Store currentUser, 
            ModelMap modelmap, 
            @ModelAttribute("storeMedia") @Valid StoreMedia storeMedia,
            BindingResult result
    ) {

        if (result.hasErrors()) {
            // add store payment types
            modelmap.addAttribute("paytypes", storePaymentTypeService.findAllByStoreId(currentUser.getId()));
            // add all payment types
            modelmap.addAttribute("allPaytypes", paymentTypeService.getAllPaymentTypes());
            // add store media
            modelmap.addAttribute("allMedia", storeMediaService.findByStore(currentUser.getId()));
            // add variable to indicate active sidebar menu
            modelmap.addAttribute("profileIsActive", "active");
            
            return "back_store/dashboard/profile";
        }
        else {
            String[] suppressedFields = result.getSuppressedFields();
            if (suppressedFields.length > 0) {
                throw new RuntimeException("Attempting to bind disallowed fields: "
                        + StringUtils.arrayToCommaDelimitedString(suppressedFields));
            }

            storeMediaService.addStoreMedia(storeMedia);
        }

        return "redirect:/store/dashboard/profile";
    }
    
    // DELETE STORE MEDIA
    @RequestMapping(value = "/media/delete/{storeMediaId}", method = RequestMethod.POST)
    public String store_dashboard_profile_deleteMedia(@PathVariable int storeMediaId) {
        storeMediaService.deleteStoreMediaByid(storeMediaId);
        return "redirect:/store/dashboard/profile";
    }

}
