/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service;

import com.ateamforce.coffeenow.model.Product;
import java.util.List;

/**
 *
 * @author alexa
 */
public interface ProductService {

    Product addProduct(Product product);

    void deleteProductByid(int productId);

    Product getProductById(int productId);

    List<Product> getAllProducts();

    List<Product> getRemainigProductsByProductCategoryId(int categoryid);

    public List<Product> getAllProductsByProductCategoryId(int categoryid);

    public void addPoductCategoriessToProduct(Product product);
}
