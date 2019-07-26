/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.ProductCategoryProduct;
import com.ateamforce.coffeenow.model.repository.ProductCategoryProductRepository;
import com.ateamforce.coffeenow.service.ProductCategoryProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexa
 */
@Service
public class ProductCategoryProductServiceImpl implements ProductCategoryProductService {
    
    @Autowired
    ProductCategoryProductRepository productCategoryProductRepository;
    
    @Override
    public void addAllProductToProductsCategory(List<ProductCategoryProduct> productCategoryProduct) {
        productCategoryProductRepository.saveAll(productCategoryProduct);
    }
    
    @Override
    public List<ProductCategoryProduct> getAllByProductCategoryid(int productcategoryid) {
        return productCategoryProductRepository.findAllByProductCategoryid(productcategoryid);
    }
    
    @Override
    public void deleteAllGivenProductCategoryProducts(List<ProductCategoryProduct> productCategoryProduct) {
        productCategoryProductRepository.deleteAll(productCategoryProduct);
    }
    
}
