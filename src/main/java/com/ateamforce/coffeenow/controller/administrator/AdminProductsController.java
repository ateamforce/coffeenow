/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.controller.administrator;

import com.ateamforce.coffeenow.editor.StringToImageEditor;
import com.ateamforce.coffeenow.model.Product;
import com.ateamforce.coffeenow.model.ProductCategory;
import com.ateamforce.coffeenow.service.ProductCategoryService;
import com.ateamforce.coffeenow.service.ProductService;
import com.ateamforce.coffeenow.validator.ProductValidator;
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
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author alexa
 */
@Controller
@RequestMapping("/administrator/dashboard/products")
public class AdminProductsController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductCategoryService productCategoryService;

    @Autowired
    ProductValidator productValidator;

    @PostMapping
    public String admin_dashboard_products_addProduct(
            ModelMap modelmap,
            @ModelAttribute("newProduct") @Valid Product newProduct,
            BindingResult result
    ) throws IOException {

        if (result.hasErrors()) {

            List<Product> products = productService.getAllProducts();
            for (Product product : products) {
                product.setProductcategoriesList(productCategoryService.getAllProductCategoriesByProductId(product.getId()));
            }

            modelmap.addAttribute("mainFormHasErrors", true);
            modelmap.addAttribute("productcategories", productCategoryService.getAllProductCategories());
            modelmap.addAttribute("products", products);
            modelmap.addAttribute("productsIsActive", "active");
            return "back_admin/dashboard/products";
        }

        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Attempting to bind disallowed fields: "
                    + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }

        productService.addProduct(newProduct);

        return "redirect:/administrator/dashboard/products";
    }

    @GetMapping("/delete/{productId}")
    public String admin_dashboard_productCategories_deleteProduct(@PathVariable int productId) {
        productService.deleteProductByid(productId);
        return "redirect:/administrator/dashboard/products";
    }

    // allowed fields for the new/update Product form returned fields
    @InitBinder
    public void initialiseBinder(WebDataBinder binder) {

        // adding custom spring validator AND reenabling JSR-303 validations that were
        // disabled because of spring validator
        binder.setValidator(productValidator);

        // setting allowed fields
        binder.setAllowedFields("id", "title", "description", "image", "productcategoriesList", "language");

        binder.registerCustomEditor(List.class, "productcategoriesList", new CustomCollectionEditor(List.class) {
            @Override
            protected ProductCategory convertElement(Object element) {
                if (element instanceof String) {
                    return productCategoryService.getProductCategoryById(Integer.parseInt(element.toString()));
                }
                return null;
            }
        });
        
        binder.registerCustomEditor(MultipartFile.class, "image", new StringToImageEditor());

    }

}
