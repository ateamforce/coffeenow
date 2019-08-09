package com.ateamforce.coffeenow.controller.store;

import com.ateamforce.coffeenow.model.Store;
import com.ateamforce.coffeenow.model.StoreProduct;
import com.ateamforce.coffeenow.service.ProductService;
import com.ateamforce.coffeenow.service.StoreProductService;
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
@RequestMapping("/store/dashboard/profile/products")
public class StoreProfileProductsController {
    
    @Autowired
    StoreProductService storeProductService;
    
    @Autowired
    ProductService productService;
    
    // PRODUCTS
    @GetMapping
    public String store_dashboard_profile_products(
            @SessionAttribute(name = "currentUser") Store currentUser, 
            ModelMap modelmap, 
            @ModelAttribute("storeProduct") StoreProduct storeProduct, 
            @ModelAttribute("mainMessage") final String msg
    ) {
        
        if (msg != null && !msg.isEmpty()) modelmap.addAttribute("mainMessage", msg);

        // add store products
        modelmap.addAttribute("storeProducts", storeProductService.findAllByStoreId(currentUser.getId()));
        
        // add products
        modelmap.addAttribute("products", productService.getAllProducts());

        // add variable to indicate active sidebar menu
        modelmap.addAttribute("productsIsActive", "active");
        
        return "back_store/dashboard/products";
    }
    
    @PostMapping
    public String store_dashboard_profile_products_addStoreProduct(
            @SessionAttribute(name = "currentUser") Store currentUser, 
            ModelMap modelmap,
            @ModelAttribute("storeProduct") @Valid StoreProduct storeProduct,
            BindingResult result
    ) throws IOException {

        if (result.hasErrors()) {
            modelmap.addAttribute("storeProducts", storeProductService.findAllByStoreId(currentUser.getId()));
            modelmap.addAttribute("products", productService.getAllProducts());
            modelmap.addAttribute("productsIsActive", "active");
            return "back_store/dashboard/products";
        }

        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Attempting to bind disallowed fields: "
                    + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }

        storeProductService.addStoreProduct(storeProduct);

        return "redirect:/store/dashboard/profile/products";
    }
    

}