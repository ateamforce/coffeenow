/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.Product;
import com.ateamforce.coffeenow.model.ProductCategory;
import com.ateamforce.coffeenow.model.repository.ProductCategoryRepository;
import com.ateamforce.coffeenow.service.ProductCategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexa
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Override
    public void addProductCategory(ProductCategory productCategory) {
        productCategoryRepository.save(productCategory);
    }

    @Override
    public void deleteProductCategoryById(int productCategoryId) {
        productCategoryRepository.deleteById(productCategoryId);
    }

    @Override
    public void updateProductCategory(ProductCategory updatedProductCategory) {
        productCategoryRepository.save(updatedProductCategory);
    }

    @Override
    public List<ProductCategory> getAllProductCategories() {
        return productCategoryRepository.findAllProductCategories();
    }

    @Override
    public ProductCategory getProductCategoryById(int categoryid) {
        return productCategoryRepository.findProductCategoryById(categoryid);
    }

    @Override
    public List<Product> getRemainigProductsByProductCategoryId(int categoryid) {
        return productCategoryRepository.findRemainigProductsByProductCategoryId(categoryid);
    }

}
