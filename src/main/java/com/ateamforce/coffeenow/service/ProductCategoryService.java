/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service;

import com.ateamforce.coffeenow.model.Product;
import com.ateamforce.coffeenow.model.ProductCategory;
import java.util.List;

/**
 *
 * @author alexa
 */
public interface ProductCategoryService {
    
    void addProductCategory(ProductCategory productCategory);
    
    void deleteProductCategoryById(int productCategoryId);
    
    void updateProductCategory(ProductCategory updatedProductCategory);
    
    List<ProductCategory>getAllProductCategories();
    
    ProductCategory getProductCategoryById(int categoryid);
    
    List<Product>getRemainigProductsByProductCategoryId(int categoryid);
    
    List<Product> getRemainigProductCategoriesByProductId(int productId);
}
