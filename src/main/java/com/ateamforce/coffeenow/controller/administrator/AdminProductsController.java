/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.controller.administrator;

import com.ateamforce.coffeenow.model.Product;
import com.ateamforce.coffeenow.service.ProductService;
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
@RequestMapping("/administrator/dashboard/products")
public class AdminProductsController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public String admin_dashboard_products_addProduct(@RequestParam("newProductJson") String newProductJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Product newProduct = mapper.readValue(newProductJson, Product.class);
        productService.addProduct(newProduct);
        return "redirect:/administrator/dashboard/products";
    }

    @GetMapping("/delete/{productId}")
    public String admin_dashboard_products_deleteProduct(@PathVariable int productId) {
        productService.deleteProductByid(productId);
        return "redirect:/administrator/dashboard/products";
    }

    @PostMapping("/update")
    public String admin_dashboard_products_updateProduct(@RequestParam("updatedProductJson") String updatedProductJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Product updatedProduct = mapper.readValue(updatedProductJson, Product.class);
        productService.updateProduct(updatedProduct);
        return "redirect:/administrator/dashboard/products";
    }
}
