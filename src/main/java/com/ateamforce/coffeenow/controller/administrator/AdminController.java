/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.controller.administrator;

import com.ateamforce.coffeenow.model.AppRole;
import com.ateamforce.coffeenow.model.Extra;
import com.ateamforce.coffeenow.model.ExtraCategory;
import com.ateamforce.coffeenow.model.PaymentType;
import com.ateamforce.coffeenow.model.Product;
import com.ateamforce.coffeenow.model.ProductCategory;
import com.ateamforce.coffeenow.service.AppRoleService;
import com.ateamforce.coffeenow.service.AppUserService;
import com.ateamforce.coffeenow.service.ExtraCategoryService;
import com.ateamforce.coffeenow.service.ExtraService;
import com.ateamforce.coffeenow.service.PaymentTypeService;
import com.ateamforce.coffeenow.service.ProductCategoryService;
import com.ateamforce.coffeenow.service.ProductService;
import com.ateamforce.coffeenow.service.StoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author alexa
 */
@Controller
@RequestMapping("/administrator/dashboard")
public class AdminController {

    @Autowired
    ProductService productService;

    @Autowired
    ExtraService extraService;

    @Autowired
    AppUserService appUserService;

    @Autowired
    ProductCategoryService productCategoryService;

    @Autowired
    ExtraCategoryService extraCategoryService;
    
    @Autowired
    StoreService storeService;

    @Autowired
    AppRoleService appRoleService;

    @Autowired
    PaymentTypeService paymentTypeService;

    /**
     * Go to administrator dashboard page
     * 
     * @param modelmap
     * @param msg Main message. Shown in div#mainMessagePanelCFN (baseLayout.jsp and loginLayout.jsp, before closing body tags). Passed as flash attributes by controller methods that redirect here
     * @return 
     */
    @RequestMapping
    public String admin_dashboard(
            ModelMap modelmap, 
            @ModelAttribute("mainMessage") final String msg
    ) {

        modelmap.addAttribute("appRoles", appRoleService.getAllAppRoles());
        modelmap.addAttribute("paymentTypes", paymentTypeService.getAllPaymentTypes());

        // add variable to indicate active sidebar
        modelmap.addAttribute("dashboardIsActive", "active");

        return "back_admin/dashboard/index";
    }

    /**
     * Gets all products to display them in products page
     * prepares add new product form
     * Go to administrator products page
     * 
     * @param modelmap
     * @param newProduct
     * @return 
     */
    @RequestMapping(value="/products", method = RequestMethod.GET)
    public String admin_dashboard_products(
            ModelMap modelmap, 
            @ModelAttribute("newProduct") Product newProduct
    ) {

        List<Product> products = productService.getAllProducts();

        products.forEach((product) -> {
            product.setProductcategoriesList(productCategoryService
                    .getAllProductCategoriesByProductId(product.getId()));
        });

        // add products
        modelmap.addAttribute("products", products);

        modelmap.addAttribute("productcategories", productCategoryService.getAllProductCategories());

        // add variable to indicate active sidebar menu
        modelmap.addAttribute("productsIsActive", "active");

        return "back_admin/dashboard/products";
    }

    /**
     * Gets all productcaregories to display them in productcategories page
     * prepares add new product category form
     * Go to administrator productcategories page
     * 
     * @param modelmap
     * @param productCategory
     * @return 
     */
    @RequestMapping(value = "/productcategories", method = RequestMethod.GET)
    public String admin_dashboard_productcategories(
            ModelMap modelmap, 
            @ModelAttribute("productCategory") ProductCategory productCategory
    ) {

        // add product categories with ExtraCategories and Products
        List<ProductCategory> productcategories = productCategoryService.getAllProductCategories();
        for (ProductCategory productcategory : productcategories) {
            productcategory.setProductsList(productService.getAllProductsByProductCategoryId(productcategory.getId()));
            productcategory.setExtrascategoriesList(extraCategoryService.getAllExtraCategoriesByProductCategoryId(productcategory.getId()));
        }

        modelmap.addAttribute("productcategories", productcategories);

        // add products
        modelmap.addAttribute("products", productService.getAllProducts());

        // add extras categories
        modelmap.addAttribute("extracategories", extraCategoryService.getAllExtraCategories());

        // add variable to indicate active sidebar menu
        modelmap.addAttribute("productcategoriesIsActive", "active");

        return "back_admin/dashboard/product_categories";
    }

    /**
     * Gets all extras to display them in extras page
     * prepares add new extra form
     * Go to administrator extras page
     * 
     * @param modelmap
     * @param newExtra
     * @return 
     */
    @RequestMapping("/extras")
    public String admin_dashboard_extras(
            ModelMap modelmap, 
            @ModelAttribute("newExtra") Extra newExtra
    ) {
        
        List<Extra> extras = extraService.getAllExtras();

        extras.forEach((extra) -> {
            extra.setExtracategoriesList(extraCategoryService
                    .getAllExtraCategoriesByExtraId(extra.getId()));
        });

        // add extras
        modelmap.addAttribute("extras", extras);
        
        modelmap.addAttribute("extracategories", extraCategoryService.getAllExtraCategories());

        // add variable to indicate active sidebar menu
        modelmap.addAttribute("extrasIsActive", "active");

        return "back_admin/dashboard/extras";
    }

    /**
     * Gets all extracaregories to display them in extracaregories page
     * prepares add new extra category form
     * Go to administrator extracaregories page
     * 
     * @param modelmap
     * @param extraCategory
     * @return 
     */
    @RequestMapping("/extracategories")
    public String admin_dashboard_extrascategories(
            ModelMap modelmap, 
            @ModelAttribute("extraCategory") ExtraCategory extraCategory
    ) {

        List<ExtraCategory> extracategories = extraCategoryService.getAllExtraCategories();
        for (ExtraCategory extracategory : extracategories) {
            extracategory.setExtrasList(extraService.getAllExtrasByExtraCategoryId(extracategory.getId()));
            extracategory.setProductcategoriesList(productCategoryService.getAllProductCategoriesByExtraCategoryId(extracategory.getId()));
        }

        // add extras categories
        modelmap.addAttribute("extracategories", extracategories);

        //add all extras
        modelmap.addAttribute("extras", extraService.getAllExtras());

        //add all product categories
        modelmap.addAttribute("productcategories", productCategoryService.getAllProductCategories());

        // add variable to indicate active sidebar menu
        modelmap.addAttribute("extracategoriesIsActive", "active");

        return "back_admin/dashboard/extra_categories";
    }
    
    /**
     * Gets all stores and enables editing them
     * 
     * @param modelmap
     * @return 
     */
    @RequestMapping("/stores")
    public String admin_dashboard_stores(
            ModelMap modelmap
    ) {
        
        //add all stores
        modelmap.addAttribute("stores", storeService.findAll());

        // add variable to indicate active sidebar menu
        modelmap.addAttribute("storesIsActive", "active");

        return "back_admin/dashboard/stores";
    }

    @PostMapping("/addapprole")
    public String admin_dashboard_addAppRole(@RequestParam("newAppRoleJson") String newAppRoleJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        AppRole newAppRole = mapper.readValue(newAppRoleJson, AppRole.class);
        appRoleService.addAppRole(newAppRole);
        return "redirect:/administrator/dashboard";
    }

    @PostMapping("/deleteapprole")
    public String admin_dashboard_deleteAppRole(@RequestParam("appRoleJson") String appRoleJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        AppRole appRole = mapper.readValue(appRoleJson, AppRole.class);
        appRoleService.deleteAppRole(appRole);
        return "redirect:/administrator/dashboard";
    }

    @PostMapping("/updateapprole")
    public String admin_dashboard_updateAppRole(@RequestParam("updatedAppRoleJson") String updatedAppRoleJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        AppRole updatedAppRole = mapper.readValue(updatedAppRoleJson, AppRole.class);
        appRoleService.updateAppRole(updatedAppRole);
        return "redirect:/administrator/dashboard";
    }

    @PostMapping("/addpaymenttype")
    public String admin_dashboard_addPaymentType(@RequestParam("newPaymentTypeJson") String newPaymentTypeJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        PaymentType newPaymentType = mapper.readValue(newPaymentTypeJson, PaymentType.class);
        paymentTypeService.addPaymentType(newPaymentType);
        return "redirect:/administrator/dashboard";
    }

    @GetMapping("/deletepaymenttype/{paymenttypeId}")
    public String admin_dashboard_deletePaymentType(@PathVariable int paymenttypeId) {
        paymentTypeService.deletePaymentTypeById(paymenttypeId);
        return "redirect:/administrator/dashboard";
    }

    @PostMapping("/updatepaymenttype")
    public String admin_dashboard_products_updateProduct(@RequestParam("updatedPaymentTypeJson") String updatedPaymentTypeJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        PaymentType updatedPaymentType = mapper.readValue(updatedPaymentTypeJson, PaymentType.class);
        paymentTypeService.updatePaymentType(updatedPaymentType);
        return "redirect:/administrator/dashboard";
    }

}
