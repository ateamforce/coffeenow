/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.dto.service;

import com.ateamforce.coffeenow.dto.ProductCategoryDto;
import java.util.List;

/**
 *
 * @author alexa
 */
public interface ProductCategoryDtoService {
    
    void addProductCategory(ProductCategoryDto productCategory);
    
    void deleteProductCategoryById(int productCategoryId);
    
    void updateProductCategory(ProductCategoryDto updatedProductCategory);
    
    List<ProductCategoryDto>getAllProductCategories();
    
    ProductCategoryDto getProductCategoryById(int categoryId);
    
    List<ProductCategoryDto> getRemainigProductCategoriesByProductId(int productId);
    
    List<ProductCategoryDto> getAllProductCategoriesByProductId(int productId);
}
