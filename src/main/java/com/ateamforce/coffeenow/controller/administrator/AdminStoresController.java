package com.ateamforce.coffeenow.controller.administrator;

import com.ateamforce.coffeenow.service.StoreService;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Sakel
 */

@Controller
@RequestMapping("/administrator/dashboard/stores")
public class AdminStoresController {

    @Autowired
    StoreService storeService;
    
    @Autowired
    private MessageSource messages;
    
    @Autowired
    LocaleResolver localeResolver;

    /**
     * ENABLE/DISABLE a store
     * 
     * @param request
     * @param enabled
     * @param storeId
     * @param attributes
     * @return 
     */
    @PostMapping
    public String admin_dashboard_stores_toggleStore(
        HttpServletRequest request, 
        @RequestParam("enabled") Integer enabled, 
        @RequestParam("storeId") Integer storeId, 
        RedirectAttributes attributes
    ){
        
        // enable/disable
        storeService.enableStoreById(enabled, storeId);
        
        // add main message
        Locale locale = localeResolver.resolveLocale(request);
        Object[] item = new Object[] {messages.getMessage("store", null, locale)};
        String mainMessage = "item.updated";
        attributes.addFlashAttribute("mainMessage", messages.getMessage(mainMessage, item, locale));
        
        return "redirect:/administrator/dashboard/stores";
    }

    /**
     * DELETE a store by id
     * 
     * @param request
     * @param storeId
     * @param attributes
     * @return 
     */
    @GetMapping("/delete/{storeId}")
    public String admin_dashboard_stores_deleteStore(
            HttpServletRequest request, 
            @PathVariable int storeId, 
            RedirectAttributes attributes
    ) {
        
        // delete
        storeService.deleteById(storeId);
        
        // add main message
        Locale locale = localeResolver.resolveLocale(request);
        Object[] item = new Object[] {messages.getMessage("store", null, locale)};
        String mainMessage = "item.deleted";
        attributes.addFlashAttribute("mainMessage", messages.getMessage(mainMessage, item, locale));
        
        return "redirect:/administrator/dashboard/stores";
    }

}
