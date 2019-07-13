/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.controller.administrator;

import com.ateamforce.coffeenow.model.Product;
import com.ateamforce.coffeenow.model.ProductCategory;
import com.ateamforce.coffeenow.service.impl.ProductCategoryServiceImpl;
import com.ateamforce.coffeenow.service.impl.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
@RequestMapping("/administrator/productcategories")
public class AdministratorProductCategoryController {

    @Autowired
    ProductCategoryServiceImpl productCategoryServiceImpl;

    @Autowired
    ProductServiceImpl productServiceImpl;

    //For the button add new productcategory
    //Post the new the new productcategory back as json string
    @PostMapping("/addproductcategory")
    public String admin_dashboard_productCategories_addProductCategory(@RequestParam("newProductCategoryJson") String newProductCategoryJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ProductCategory newProductCategory = mapper.readValue(newProductCategoryJson, ProductCategory.class);
        productCategoryServiceImpl.addProductCategory(newProductCategory);
        return "redirect:/administrator/dashboard/productcategories";//Probably load the productcategories index page again
    }

    //For the button delete product category
    //Get only the id of the product category 
    @GetMapping("/deleteproductcategory/{productCategoryId}")
    public String admin_dashboard_productCategories_deleteProductCategory(@PathVariable int productCategoryId) {
        productCategoryServiceImpl.deleteProductCategoryById(productCategoryId);
        return "redirect:/administrator/dashboard/productcategories";//Probably load the productcategories index page again
    }

    //For the button update product category
    //Post the updated product category back as json string
    @PostMapping("/updateproductcategory")
    public String admin_dashboard_productCategories_updateProductCategory(@RequestParam("updatedProductCategoryJson") String updatedProductCategoryJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ProductCategory updatedProductCategory = mapper.readValue(updatedProductCategoryJson, ProductCategory.class);
        productCategoryServiceImpl.updateProductCategory(updatedProductCategory);
        return "redirect:/administrator/dashboard/products";//Probably load the products index page again
    }

    //For the button add/remove products 
    //Get the product category by the id
    //Feed all the necessary infos to productcategoryaddremove page
    @GetMapping("/removeaddproducts/{productCategoryId}")
    public String admin_dashboard_productCategories_removeaddproducts(ModelMap modelMap, @PathVariable int productCategoryId) {
        ProductCategory productCategory = productCategoryServiceImpl.getProductCategoryById(productCategoryId);
        List<Product> categoryProducts = (List<Product>) productCategory.getProductsCollection();
        List<Product> remaningProducts = productCategoryServiceImpl.getRemainigProductsByProductCategoryId(productCategoryId);
        modelMap.addAttribute("categoryProducts", categoryProducts);
        modelMap.addAttribute("remaningProducts", remaningProducts);
        modelMap.addAttribute("productCategory", productCategory);
        return "product_categories/productcategoryaddremove";
    }

    //For the button remove
    //Get category and product by ids
    @GetMapping("/removeproduct/{productCategoryId}/{productId}")
    public String admin_dashboard_productCategories_removeproduct(@PathVariable int productCategoryid, @PathVariable int productId) {
        ProductCategory productCategory = productCategoryServiceImpl.getProductCategoryById(productCategoryid);
        List<Product> categoryProducts = (List<Product>) productCategory.getProductsCollection();
        Product productToRemove = productServiceImpl.getProductById(productId);
        categoryProducts.remove(productToRemove);
        return "/administrator/productcategories/removeaddproducts/" + productCategory.getId();
    }

    //For the button add
    //Get category by id
    //Get product by id (from a dropdown list)
    @GetMapping("/addproduct/{productCategoryId}/{productId}")
    public String admin_dashboard_productCategories_addproduct(@PathVariable int productCategoryid, @PathVariable int productId) {
        ProductCategory productCategory = productCategoryServiceImpl.getProductCategoryById(productCategoryid);
        List<Product> categoryProducts = (List<Product>) productCategory.getProductsCollection();
        Product productToAdd = productServiceImpl.getProductById(productId);
        categoryProducts.add(productToAdd);
        return "/administrator/productcategories/removeaddproducts/" + productCategory.getId();
    }

}
