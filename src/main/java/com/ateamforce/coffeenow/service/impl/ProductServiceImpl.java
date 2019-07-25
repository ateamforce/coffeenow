/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.Product;
import com.ateamforce.coffeenow.model.repository.ProductRepository;
import com.ateamforce.coffeenow.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alexa
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProductByid(int productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllProducts();
    }

    @Override
    public Product getProductById(int productId) {
        return productRepository.findProductById(productId);
    }

    @Override
    public void updateProduct(Product updatedProduct) {
        productRepository.save(updatedProduct);
    }
    
    @Override
    public List<Product> getRemainigProductsByProductCategoryId(int categoryid) {
        return productRepository.findRemainigProductsByProductCategoryId(categoryid);
    }

}
