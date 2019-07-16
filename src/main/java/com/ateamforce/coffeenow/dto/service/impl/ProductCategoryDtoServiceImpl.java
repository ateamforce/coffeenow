/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.dto.service.impl;

import com.ateamforce.coffeenow.dto.ProductCategoryDto;
import com.ateamforce.coffeenow.dto.repository.ProductCategoryDtoRepository;
import com.ateamforce.coffeenow.dto.service.ProductCategoryDtoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexa
 */
@Service
public class ProductCategoryDtoServiceImpl implements ProductCategoryDtoService {

    @Autowired
    ProductCategoryDtoRepository productCategoryDtoRepository;

    @Override
    public void addProductCategory(ProductCategoryDto productCategory) {
        productCategoryDtoRepository.save(productCategory);
    }

    @Override
    public void deleteProductCategoryById(int productCategoryId) {
        productCategoryDtoRepository.deleteById(productCategoryId);
    }

    @Override
    public void updateProductCategory(ProductCategoryDto updatedProductCategory) {
        productCategoryDtoRepository.save(updatedProductCategory);
    }

    @Override
    public List<ProductCategoryDto> getAllProductCategories() {
        return productCategoryDtoRepository.findAllProductCategories();
    }

    @Override
    public ProductCategoryDto getProductCategoryById(int categoryId) {
        return productCategoryDtoRepository.findProductCategoryById(categoryId);
    }

    @Override
    public List<ProductCategoryDto> getRemainigProductCategoriesByProductId(int productId) {
        return productCategoryDtoRepository.findRemainigProductCategoriesByProductId(productId);
    }

    @Override
    public List<ProductCategoryDto> getAllProductCategoriesByProductId(int productId) {
        return productCategoryDtoRepository.findAllProductCategoriesByProductId(productId);
    }

}
