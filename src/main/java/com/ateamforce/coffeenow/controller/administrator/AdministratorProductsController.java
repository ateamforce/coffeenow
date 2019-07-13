/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.controller.administrator;

import com.ateamforce.coffeenow.model.Product;
import com.ateamforce.coffeenow.service.impl.ProductServiceImpl;
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
@RequestMapping("/administrator/products")
public class AdministratorProductsController {

    @Autowired
    ProductServiceImpl productserviceimpl;

    //For the button add new product
    //Post the new the new product back as json string
    @PostMapping("/addproduct")
    public String admin_dashboard_products_addProduct(@RequestParam("newProductJson") String newProductJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Product newProduct = mapper.readValue(newProductJson, Product.class);
        productserviceimpl.addProduct(newProduct);
        return "redirect:/administrator/dashboard/products";//Probably load the products index page again
    }

    //For the button delete product
    //Get only the id of the product 
    @GetMapping("/deleteproduct/{productid}")
    public String admin_dashboard_products_deleteProduct(@PathVariable int productid) {
        productserviceimpl.deleteProductByid(productid);
        return "redirect:/administrator/dashboard/products";//Probably load the products index page again
    }

    //For the button update product
    //Post the updated product back as json string
    @PostMapping("/updateproduct")
    public String admin_dashboard_products_updateProduct(@RequestParam("updatedProductJson") String updatedProductJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Product updatedProduct = mapper.readValue(updatedProductJson, Product.class);
        productserviceimpl.updateProduct(updatedProduct);
        return "redirect:/administrator/dashboard/products";//Probably load the products index page again
    }
}
