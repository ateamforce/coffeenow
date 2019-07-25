/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service;

import com.ateamforce.coffeenow.model.ProductCategory;
import java.util.List;

/**
 *
 * @author alexa
 */
public interface ProductCategoryService {
    
    ProductCategory addProductCategory(ProductCategory productCategory);
    
    void deleteProductCategoryById(int productCategoryId);
    
    List<ProductCategory>getAllProductCategories();
    
    ProductCategory getProductCategoryById(int categoryId);
    
    List<ProductCategory> getRemainigProductCategoriesByProductId(int productId);
    
    public List<ProductCategory> getAllProductCategoriesByExtraCategoryId(int extraCategoryId);
    
    public void addExtraCategoriesToProductCategory(ProductCategory productCategory);
    
    public void addPoductsToProductCategory(ProductCategory productCategory);
}
